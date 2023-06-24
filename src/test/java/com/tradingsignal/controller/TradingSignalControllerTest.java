package com.tradingsignal.controller;

import com.tradingsignal.document.TradingSignal;
import com.tradingsignal.service.TradingSignalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SpringBootTest
class TradingSignalControllerTest {
    @Mock
    private TradingSignalService tradingSignalService;
    @InjectMocks
    private TradingSignalController tradingSignalController;

    @Test
    void testCallSignalHandler_invalidUserInput() {
        ResponseEntity<String> response = tradingSignalController.callSignalHandler(0);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testCallSignalHandler_callingHandleSignal() {
        TradingSignal ts = new TradingSignal(1, Map.of(1, "setUp()", 2, "Reverse()"));
        Mockito.when(tradingSignalService.getTradingSignals(ArgumentMatchers.anyInt())).thenReturn(ts);

        ResponseEntity<String> response = tradingSignalController.callSignalHandler(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}