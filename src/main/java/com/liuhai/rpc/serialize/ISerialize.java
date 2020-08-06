package com.liuhai.rpc.serialize;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/17
 * Time: 23:24
 * Description: No Description
 */
public interface ISerialize {

    /**
     * 序列化对象
     * @param t
     * @param <T>
     * @return
     */
     <T> byte[] serialize(T t);

    /**
     * 反序列化对象
     * @param bytes
     * @param <T>
     * @return
     */
     <T> T deSerialize(byte[] bytes, Class<T> clazz);
}
