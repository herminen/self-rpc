package com.liuhai.rpc.serialize.impl;

import com.liuhai.rpc.serialize.ISerialize;
import com.liuhai.rpc.util.SerializableUtil;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 22:30
 * Description: No Description
 */
public class Protobuf implements ISerialize {
    @Override
    public <T> byte[] serialize(T t) {
        return SerializableUtil.serialize(t);
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> clazz) {
        return SerializableUtil.deserialize(bytes,clazz );
    }
}
