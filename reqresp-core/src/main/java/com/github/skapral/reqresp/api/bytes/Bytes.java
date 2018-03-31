package com.github.skapral.reqresp.api.bytes;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Bytes {
    int size();
    <T> T toObject(Function<InputStream, T> fn);
    void read(Consumer<InputStream> fn);
}
