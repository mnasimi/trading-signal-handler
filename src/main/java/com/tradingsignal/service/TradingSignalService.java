package com.tradingsignal.service;

import com.tradingsignal.document.TradingSignal;
import org.springframework.stereotype.Service;

/**
 * The service for handling trading signal db-related tasks.
 */
@Service
public class TradingSignalService {
    /**
     * Retrieve signal from db..
     */
    public TradingSignal getTradingSignals(Integer signal) {
        return new TradingSignal();
    }

}
