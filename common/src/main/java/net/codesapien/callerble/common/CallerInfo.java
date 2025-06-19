package net.codesapien.callerble.common;

import java.util.Objects;

/**
 * DTO representing caller information to be shared over BLE.
 */
public class CallerInfo {
    private final String name;
    private final String phoneNumber;
    private final long timestamp;

    public CallerInfo(String name, String phoneNumber, long timestamp) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallerInfo that = (CallerInfo) o;
        return timestamp == that.timestamp &&
                Objects.equals(name, that.name) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, timestamp);
    }

    @Override
    public String toString() {
        return "CallerInfo{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
} 