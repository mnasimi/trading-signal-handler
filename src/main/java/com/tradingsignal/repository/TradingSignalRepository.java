package com.tradingsignal.repository;

import com.tradingsignal.document.TradingSignal;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for managing trading signals
 */
public interface TradingSignalRepository extends MongoRepository<TradingSignal, String> {
}
