package net.codesapien.callerble.service.impl;

import net.codesapien.callerble.common.CallerInfo;
import net.codesapien.callerble.common.BleConstants;
import net.codesapien.callerble.service.BleListener;
import com.gluonhq.attach.ble.BleService;
import com.gluonhq.attach.ble.Configuration;
import java.util.function.Consumer;

/**
 * Default stub implementation of BleListener using Gluon Attach BLE.
 */
public class DefaultBleListener implements BleListener {

    private final com.gluonhq.attach.ble.BleService service = com.gluonhq.attach.ble.BleService.create()
            .orElseThrow(() -> new RuntimeException("BLE service not available"));

    @Override
    public void startListening(Consumer<CallerInfo> callback) {
        // Configure scanning to filter for our fixed beacon SERVICE_UUID
        Configuration conf = new Configuration(BleConstants.SERVICE_UUID.toString());
        service.startScanning(conf, scan -> {
            // Create a CallerInfo stub for manual testing using scan major/minor
            String name = "DetectedBeacon";
            String phone = scan.getMajor() + "/" + scan.getMinor();
            CallerInfo info = new CallerInfo(name, phone, System.currentTimeMillis());
            callback.accept(info);
        });
    }

    @Override
    public void stopListening() {
        service.stopScanning();
    }
} 