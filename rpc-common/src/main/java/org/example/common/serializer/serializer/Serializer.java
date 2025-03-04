package org.example.common.serializer.serializer;


import org.example.common.serializer.serializer.impl.JsonSerializer;
import org.example.common.serializer.serializer.impl.ObjectSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public interface Serializer {
    byte[] serialize(Object object);
    Object deserialize(byte[] bytes, int messageType);
    int getType();
    static final Map<Integer, Serializer> serializerMap = new ConcurrentHashMap<>();
    static Serializer getSerializer(int type) {
        if (serializerMap.isEmpty()) {
            serializerMap.put(0, new ObjectSerializer());
            serializerMap.put(1, new JsonSerializer());
        }
        return serializerMap.getOrDefault(type, serializerMap.get(0));
    }
}
