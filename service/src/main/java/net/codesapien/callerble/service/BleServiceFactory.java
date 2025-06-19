package net.codesapien.callerble.service;

import java.util.ServiceLoader;

/**
 * Factory for loading BLE service implementations via Java ServiceLoader.
 */
public final class BleServiceFactory {

    private static final BleBroadcaster broadcaster = ServiceLoader
            .load(BleBroadcaster.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No BleBroadcaster implementation found"));

    private static final BleListener listener = ServiceLoader
            .load(BleListener.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No BleListener implementation found"));

    private BleServiceFactory() {}

    /**
     * Get the BleBroadcaster implementation.
     * @return BleBroadcaster
     */
    public static BleBroadcaster getBroadcaster() {
        return broadcaster;
    }

    /**
     * Get the BleListener implementation.
     * @return BleListener
     */
    public static BleListener getListener() {
        return listener;
    }
} 