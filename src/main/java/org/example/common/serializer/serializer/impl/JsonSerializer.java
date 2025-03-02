package org.example.common.serializer.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;
import org.example.common.serializer.serializer.Serializer;

public class JsonSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }
    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object object = null;
        switch (messageType) {
            case 0:
                RpcRequest request = JSON.parseObject(bytes, RpcRequest.class);
                Object[] params = new Object[request.getParams().length];
                for (int i = 0; i < params.length; i++) {
                    Class<?> paramType = request.getParamsType()[i];
                    Object param = request.getParams()[i];
                    if (paramType.isAssignableFrom(param.getClass())) {
                        params[i] = param;
                    } else {
                        params[i] = JSONObject.toJavaObject((JSONObject) param, paramType);
                    }
                }
                request.setParams(params);
                object = request;
                break;
            case 1:
                RpcResponse response = JSON.parseObject(bytes, RpcResponse.class);
                Class<?> dataType = response.getDataType();
                if (dataType != null && !dataType.isAssignableFrom(response.getData().getClass())) {
                    response.setData(JSONObject.toJavaObject((JSONObject) response.getData(), dataType));
                }
                object = response;
                break;
            default:
                System.out.println("不支持该消息类型");
                throw new RuntimeException();
        }
        return object;
    }
    @Override
    public int getType() {
        return 1;
    }
}
