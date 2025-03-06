package org.example.common.serializer.serializer.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;
import org.example.common.message.proto.RpcRequestProto;
import org.example.common.message.proto.RpcResponseProto;
import org.example.common.serializer.serializer.Serializer;

import java.util.ArrayList;
import java.util.List;

public class ProtobufSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
//        System.out.println("protobuf serialize");
        if (object instanceof RpcRequest) {
            RpcRequest request = (RpcRequest) object;
            RpcRequestProto.RpcRequest.Builder builder = RpcRequestProto.RpcRequest.newBuilder()
                    .setInterfaceName(request.getInterfaceName())
                    .setMethodName(request.getMethodName());
            if (request.getParams() != null) {
                for (int i = 0; i < request.getParams().length; i++) {
                    builder.addParams(serializeObject(request.getParams()[i]));
                    builder.addParamsType(request.getParamsType()[i].getName());
                }
            }
            return builder.build().toByteArray();
        } else if (object instanceof RpcResponse) {
            RpcResponse response = (RpcResponse) object;
            RpcResponseProto.RpcResponse.Builder builder = RpcResponseProto.RpcResponse.newBuilder()
                    .setCode(response.getCode())
                    .setMessage(response.getMessage());
            if (response.getData() != null) {
                builder.setData(serializeObject(response.getData()));
                builder.setDataType(response.getDataType().getName());
            }
            return builder.build().toByteArray();
        } else {
            throw new IllegalArgumentException("ProtobufSerializer only supports RpcRequest and RpcResponse objects");
        }
    }

    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        try {
            switch (messageType) {
                case 0:
                    RpcRequestProto.RpcRequest requestProto = RpcRequestProto.RpcRequest.parseFrom(bytes);
                    RpcRequest request = new RpcRequest();
                    request.setInterfaceName(requestProto.getInterfaceName());
                    request.setMethodName(requestProto.getMethodName());
                    request.setParams(deserializeObjects(requestProto.getParamsList(), requestProto.getParamsTypeList()));
                    request.setParamsType(deserializeTypes(requestProto.getParamsTypeList()));
                    return request;
                case 1:
                    RpcResponseProto.RpcResponse responseProto = RpcResponseProto.RpcResponse.parseFrom(bytes);
                    RpcResponse response = new RpcResponse();
                    response.setCode(responseProto.getCode());
                    response.setMessage(responseProto.getMessage());
                    if (!responseProto.getData().isEmpty()) {
                        response.setData(deserializeObject(responseProto.getData().toByteArray(), Class.forName(responseProto.getDataType())));
                        response.setDataType(Class.forName(responseProto.getDataType()));
                    }
                    return response;
                default:
                    throw new IllegalArgumentException("Unsupported message type: " + messageType);
            }
        } catch (InvalidProtocolBufferException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to deserialize Protobuf message", e);
        }
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public String toString() {
        return "Protobuf";
    }

    // 序列化单个对象为字节数组
    private ByteString serializeObject(Object object) {
        // 使用 Protobuf 的 ByteString 来序列化对象
        return com.google.protobuf.ByteString.copyFromUtf8(object.toString());
    }

    // 反序列化单个对象
    private Object deserializeObject(byte[] bytes, Class<?> clazz) {
        // 使用 Protobuf 的 ByteString 来反序列化对象
        try {
            String value = com.google.protobuf.ByteString.copyFrom(bytes).toStringUtf8();
            return clazz.getConstructor(String.class).newInstance(value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 批量反序列化对象
    private Object[] deserializeObjects(List<com.google.protobuf.ByteString> paramsList, List<String> paramsTypeList) {
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < paramsList.size(); i++) {
            try {
                Class<?> clazz = Class.forName(paramsTypeList.get(i));
                result.add(deserializeObject(paramsList.get(i).toByteArray(), clazz));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Failed to deserialize type: " + paramsTypeList.get(i), e);
            }
        }
        return result.toArray();
    }

    // 反序列化类型名称为 Class 对象
    private Class<?>[] deserializeTypes(List<String> typeNames) {
        List<Class<?>> result = new ArrayList<>();
        for (String typeName : typeNames) {
            try {
                result.add(Class.forName(typeName));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Failed to deserialize type: " + typeName, e);
            }
        }
        return result.toArray(new Class<?>[0]);
    }
}