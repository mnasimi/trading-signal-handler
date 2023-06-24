package com.tradingsignal.handler;

/**
 * This is an upcall from our trading system, and we cannot change it.
 */
interface ISignalHandler {
    void handleSignal(int signal);
}

