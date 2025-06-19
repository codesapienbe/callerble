package net.codesapien.callerble.common;

import java.util.UUID;

/**
 * Constants used for BLE beacon advertising and scanning in CallerBLE.
 */
public final class BleConstants {
    /**
     * Fixed UUID for CallerBLE BLE beacon service.
     */
    public static final UUID SERVICE_UUID = UUID.fromString("12345678-1234-1234-1234-1234567890ab");

    /**
     * Major value for beacon advertisement (manual test fixed value).
     */
    public static final int MAJOR = 1;

    /**
     * Minor value for beacon advertisement (manual test fixed value).
     */
    public static final int MINOR = 1;

    /**
     * Identifier used by BLE service for the advertising instance.
     */
    public static final String IDENTIFIER = "callerble";

    private BleConstants() {
        // prevent instantiation
    }
} 