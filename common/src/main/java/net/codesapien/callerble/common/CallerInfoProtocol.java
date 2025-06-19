package net.codesapien.callerble.common;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Protocol implementation for encoding and decoding CallerInfo
 * messages over BLE.
 *
 * Format:
 * [timestamp (8 bytes)]
 * [name length (4 bytes)] [name UTF-8 bytes]
 * [phone length (4 bytes)] [phone UTF-8 bytes]
 */
public class CallerInfoProtocol implements BleMessageProtocol<CallerInfo> {

    @Override
    public byte[] serialize(CallerInfo info) {
        byte[] nameBytes = info.getName().getBytes(StandardCharsets.UTF_8);
        byte[] phoneBytes = info.getPhoneNumber().getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(
                Long.BYTES +
                Integer.BYTES + nameBytes.length +
                Integer.BYTES + phoneBytes.length
        );
        buffer.putLong(info.getTimestamp());
        buffer.putInt(nameBytes.length);
        buffer.put(nameBytes);
        buffer.putInt(phoneBytes.length);
        buffer.put(phoneBytes);
        return buffer.array();
    }

    @Override
    public CallerInfo deserialize(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        long timestamp = buffer.getLong();
        int nameLen = buffer.getInt();
        byte[] nameBytes = new byte[nameLen];
        buffer.get(nameBytes);
        int phoneLen = buffer.getInt();
        byte[] phoneBytes = new byte[phoneLen];
        buffer.get(phoneBytes);
        String name = new String(nameBytes, StandardCharsets.UTF_8);
        String phone = new String(phoneBytes, StandardCharsets.UTF_8);
        return new CallerInfo(name, phone, timestamp);
    }
} 