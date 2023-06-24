package com.tradingsignal.service;

import com.tradingsignal.document.TradingSignal;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * The service for handling trading signal db-related tasks.
 */
@Service
public class TradingSignalService {
    /**
     * Retrieve signal from db..
     */
    public TradingSignal getTradingSignals(Integer signal) {
        // working with dummy data
        return new TradingSignal(1, Map.of(1, "setUp()", 2 , "reverse()"));
    }

}
