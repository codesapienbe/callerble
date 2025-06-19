package net.codesapien.callerble.service.impl;

import net.codesapien.callerble.common.CallerInfo;
import net.codesapien.callerble.common.BleConstants;
import net.codesapien.callerble.service.BleBroadcaster;
import com.gluonhq.attach.ble.BleService;

/**
 * Default stub implementation of BleBroadcaster using Gluon Attach BLE.
 */
public class DefaultBleBroadcaster implements BleBroadcaster {

    private final com.gluonhq.attach.ble.BleService service = com.gluonhq.attach.ble.BleService.create()
            .orElseThrow(() -> new RuntimeException("BLE service not available"));

    @Override
    public void broadcast(CallerInfo info) {
        // Broadcast as a BLE beacon using fixed SERVICE_UUID, MAJOR, MINOR
        service.startBroadcasting(
                BleConstants.SERVICE_UUID,
                BleConstants.MAJOR,
                BleConstants.MINOR,
                BleConstants.IDENTIFIER
        );
    }
} 