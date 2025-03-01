package org.example.common.serializer.serializer;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializer implements Serializer{
    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
            bytes = bos.toByteArray();
            bos.close();
            out.close();
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
            ObjectInputStream in = new ObjectInputStream(bis);
            object = in.readObject();
            bis.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return object;
    }
    @Override
    public int getType() {
        return 0;
    }
}
