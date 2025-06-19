package net.codesapien.callerble.service;

import net.codesapien.callerble.common.CallerInfo;
import java.util.function.Consumer;

/**
 * Defines a BLE listener for receiving CallerInfo messages.
 */
public interface BleListener {

    /**
     * Start listening for incoming CallerInfo messages.
     *
     * @param callback callback invoked when a new CallerInfo is received
     */
    void startListening(Consumer<CallerInfo> callback);

    /**
     * Stop listening for CallerInfo messages.
     */
    void stopListening();
} 