package com.tradingsignal.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * An entity represents the document in database
 */
@Data
@NoArgsConstructor
@Document(collection = "signals")
public class TradingSignal {
    @Id
    private String id;
    private int signal;
    private Map<Integer, String> actions;

    public TradingSignal(int signal, Map<Integer, String> actions) {
        this.signal = signal;
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "TradingSignal{" +
                "signal=" + signal +
                ", actions=" + actions +
                '}';
    }
}
