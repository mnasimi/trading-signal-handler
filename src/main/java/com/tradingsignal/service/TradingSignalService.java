package com.tradingsignal.service;

import com.tradingsignal.document.TradingSignal;
import com.tradingsignal.repository.TradingSignalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The service for handling trading signal db-related tasks.
 */
@Service
public class TradingSignalService {
    private TradingSignalRepository tradingSignalRepository;

    @Autowired
    public TradingSignalService(TradingSignalRepository tradingSignalRepository) {
        this.tradingSignalRepository = tradingSignalRepository;
    }

    /**
     * Retrieve signal from db.
     */
    public TradingSignal getTradingSignals(Integer signal) {
        return tradingSignalRepository.findBySignal(signal);
    }

}
