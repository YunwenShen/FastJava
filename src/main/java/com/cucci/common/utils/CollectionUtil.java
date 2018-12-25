package com.cucci.common.utils;

import java.io.*;
import java.util.List;

/**
 * 集合类工具类
 *
 * @author shenyw
 **/
public class CollectionUtil {

    private CollectionUtil() {
    }

    /**
     * 深拷贝
     *
     * @param list 数组列表
     * @param <T>  数组类型
     * @return 拷贝结果
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deepCopy(List<T> list) throws IOException, ClassNotFoundException {
        if (!(list instanceof Serializable)) {
            throw new IllegalArgumentException("args must be serializable");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(list);
        //从流中取出
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (T) objectInputStream.readObject();
    }
}
