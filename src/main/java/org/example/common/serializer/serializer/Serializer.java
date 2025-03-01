package org.example.common.serializer.serializer;


import org.example.common.serializer.serializer.imple.JsonSerializer;
import org.example.common.serializer.serializer.imple.ObjectSerializer;

public interface Serializer {
    byte[] serialize(Object object);
    Object deserialize(byte[] bytes, int messageType);
    int getType();
    static Serializer getSerializer(int type) {
        switch (type) {
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
