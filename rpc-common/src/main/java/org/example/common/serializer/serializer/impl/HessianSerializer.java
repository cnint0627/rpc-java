package org.example.common.serializer.serializer.impl;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.example.common.serializer.serializer.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HessianSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            HessianOutput hessianOutput = new HessianOutput(bos);
            hessianOutput.writeObject(object);
            bytes = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object object = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            HessianInput hessianInput = new HessianInput(bis);
            object = hessianInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
    @Override
    public int getType() {
        return 2;
    }
    @Override
    public String toString() {
        return "Hessian";
    }
}
