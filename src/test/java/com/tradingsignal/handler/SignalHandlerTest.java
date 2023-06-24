package com.tradingsignal.handler;

import com.tradingsignal.document.TradingSignal;
import com.tradingsignal.lib.Algo;
import com.tradingsignal.service.TradingSignalService;
import com.tradingsignal.utils.MethodMapBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
class SignalHandlerTest {
    @Mock
    private TradingSignalService tradingSignalService;
    @Mock
    private MethodMapBuilder methodMapBuilder;
    @Mock
    private Algo algo;
    @InjectMocks
    SignalHandler signalHandler;

    @Test
    void testHandleSignal() throws Exception {
        // Test data
        Map<Integer, String> actions = new HashMap<>();
        actions.put(1, "setUp");
        actions.put(2, "performCalc");
        TradingSignal ts = new TradingSignal(1, actions);

        // Mock the behavior of the signalActionLibrary
        Map<String, Method> actionMethods = new HashMap<>();
        actionMethods.put("setUp", Algo.class.getMethod("setUp"));
        actionMethods.put("performCalc", Algo.class.getMethod("performCalc"));
        when(methodMapBuilder.build(ArgumentMatchers.any())).thenReturn(actionMethods);
        Mockito.when(tradingSignalService.getTradingSignals(ArgumentMatchers.anyInt())).thenReturn(ts);

        signalHandler.handleSignal(ts.getSignal());
        Mockito.verify(methodMapBuilder).build(Algo.class);
    }

    // TODO: the missing test are:
    // - verify the invocation of the actions
    // - verify the order of actions calls
}