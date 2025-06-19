package net.codesapien.callerble.common;

/**
 * Interface defining serialization and deserialization of objects
 * to and from BLE message payloads.
 *
 * @param <T> The type of object to encode/decode.
 */
public interface BleMessageProtocol<T> {

    /**
     * Serialize the object to a byte array for BLE transmission.
     *
     * @param obj The object to serialize.
     * @return The byte array representation.
     */
    byte[] serialize(T obj);

    /**
     * Deserialize the object from a byte array payload received via BLE.
     *
     * @param data The byte array payload.
     * @return The deserialized object.
     */
    T deserialize(byte[] data);
} 