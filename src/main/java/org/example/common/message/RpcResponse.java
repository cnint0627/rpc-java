package org.example.common.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    private Object data;
    private Class<?> dataType;
    public static RpcResponse success(Object data) {
        return RpcResponse.builder()
                .code(200)
                .data(data)
                .dataType(data.getClass())
                .build();
    }
    public static RpcResponse fail() {
        return RpcResponse.builder()
                .code(500)
                .message("服务器内部错误")
                .dataType(Object.class)
                .build();
    }
}
