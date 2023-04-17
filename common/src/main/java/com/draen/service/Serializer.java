package com.draen.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

public interface Serializer<T> {
    void serialize(T item, Writer writer) throws IOException;
    Optional<T> deserialize(BufferedReader reader) throws IOException;
}
