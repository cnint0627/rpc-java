package org.example.common.serializer.serializer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SerializerType {
    OBJECT(0), JSON(1), HESSIAN(2), PROTOBUF(3);
    private int code;
    public int getCode() {
        return code;
    }
}
