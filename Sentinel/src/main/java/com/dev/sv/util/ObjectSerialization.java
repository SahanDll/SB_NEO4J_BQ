package com.dev.sv.util;

import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import java.nio.ByteBuffer;
import java.util.Collection;

public class ObjectSerialization implements Serializer<Collection<Object>> {
    @Override
    public ByteBuffer serialize(Collection<Object> objects) throws SerializerException {
        return null;
    }

    @Override
    public Collection<Object> read(ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {
        return null;
    }

    @Override
    public boolean equals(Collection<Object> objects, ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {
        return false;
    }
}
