package com.cucci.common.caches.serializer;

import java.io.*;

/**
 * JDK序列化和反序列化对象
 *
 * @author shenyw
 **/
public class JdkSerializer implements ISerializer {

    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream(1024);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), e);
        }
        return byteArrayStream.toByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] byteArray, Class<T> clazz) {
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArray));
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to deserialize byteArray, the reason", e);
        }
    }
}
