package org.example.common.serializer.serializer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SerializerType {
    OBJECT(0), JSON(1);
    private int code;
    public int getCode() {
        return code;
    }
}
