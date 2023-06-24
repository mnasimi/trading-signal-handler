package com.tradingsignal.handler;

import com.tradingsignal.document.TradingSignal;
import com.tradingsignal.lib.Algo;
import com.tradingsignal.service.TradingSignalService;
import com.tradingsignal.utils.MethodMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * A component to handle the received signal
 */
@Component
public class SignalHandler implements ISignalHandler {
    private final TradingSignalService tradingSignalService;
    private final MethodMapBuilder methodMapBuilder;
    @Autowired
    public SignalHandler(TradingSignalService tradingSignalService, MethodMapBuilder methodMapBuilder) {
        this.tradingSignalService = tradingSignalService;
        this.methodMapBuilder = methodMapBuilder;
    }

    @Override
    public void handleSignal(int signal) {
        TradingSignal tradingSignal = tradingSignalService.getTradingSignals(signal);
        if (tradingSignal == null) {
            //if the signal is not valid -> the trade will be cancelled.
            cancelTrade();
        } else {
            //Load signal-action from library
            Map<String, Method> libMethodMap = methodMapBuilder.build(Algo.class);
            // Sorting signal actions
            Map<Integer, String> sortedMap = sortingMap(tradingSignal.getActions());
            // Loop over the action to invoke them
            sortedMap.forEach((order, action) -> {
                Pair<String, int[]> actionData = extractStringAndArguments(action);
                String actionName = actionData.getFirst();
                int[] arguments = actionData.getSecond();
                if (libMethodMap.containsKey(actionName)) {
                    // Invoke the method
                    invokeMethod(libMethodMap.get(actionName), arguments);
                } else {
                    System.out.println("Unknown signal action: please check the defined action data (signal: " + tradingSignal.getSignal() + " Action name: " + action + ")");
                }
            });
        }
    }

    /**
     * To be sure that actions invoked in the given order.
     */
    private Map<Integer, String> sortingMap(Map<Integer, String> map) {
        Map<Integer, String> sortedMap = new TreeMap<>(Comparator.naturalOrder());
        sortedMap.putAll(map);
        return sortedMap;
    }

    /**
     * Extract action name and argument.
     * TODO: this is fragile. More robust solution is desirable.
     */
    private Pair<String, int[]> extractStringAndArguments(String action) {
        // removing parenthesis and digits
        String actionName = action
                .replaceAll("[\\[\\](){}]", "")
                .replaceAll("[0-9]", "")
                .replaceAll(",", "");
        //Extracting integer arguments
        int[] arguments = Arrays.stream(action.replaceAll("[^0-9]+", " ").split(" "))
                .filter(x -> !x.equals(""))
                .map(Integer::valueOf)
                .mapToInt(x -> x)
                .toArray();
        return Pair.of(actionName, arguments);
    }

    /**
     * Invoking methods with arguments
     */
    private void invokeMethod(Method method, int[] arguments) {
        try {
            if (arguments != null && arguments.length > 0) {
                //TODO: The number arguments should be dynamic. Need further attention.
                method.invoke(new Algo(), arguments[0], arguments[1]);
            } else {
                method.invoke(new Algo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * In the case of wrong signal, trade should be cancelled.
     */
    private void cancelTrade() {
        Algo algo = new Algo();
        algo.cancelTrades();
    }
}
