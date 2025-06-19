package net.codesapien.callerble.service;

import net.codesapien.callerble.common.CallerInfo;

/**
 * Defines a BLE broadcaster for sending CallerInfo messages.
 */
public interface BleBroadcaster {

    /**
     * Broadcasts the specified CallerInfo over BLE.
     *
     * @param info the CallerInfo to broadcast
     */
    void broadcast(CallerInfo info);
} 