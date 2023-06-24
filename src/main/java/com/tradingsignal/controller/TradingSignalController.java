package com.tradingsignal.controller;

import com.tradingsignal.handler.SignalHandler;
import com.tradingsignal.service.TradingSignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for handling the trading signals
 */
@RestController
@RequestMapping("api")
public class TradingSignalController {
    private TradingSignalService tradingSignalService;
    private SignalHandler signalHandler;

    @Autowired
    public TradingSignalController(TradingSignalService tradingSignalService, SignalHandler signalHandler) {
        this.tradingSignalService = tradingSignalService;
        this.signalHandler = signalHandler;
    }

    @PostMapping("/signal/{signal}")
    public ResponseEntity<String> callSignalHandler(@PathVariable int signal) {
        // check user input
        if (signal == 0) {
            return new ResponseEntity<String>("User input is not valid", HttpStatus.BAD_REQUEST);
        }
        signalHandler.handleSignal(signal);
        return ResponseEntity.ok().build();
    }
}
