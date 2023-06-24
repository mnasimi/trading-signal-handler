package com.tradingsignal.controller;

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

    @PostMapping("/signal/{signal}")
    public ResponseEntity<String> callSignalHandler(@PathVariable int signal) {
        // check user input
        // call the service
        return ResponseEntity.ok().build();
    }
}
