package com.github.skapral.reqresp.api.bytes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class BString implements Bytes {
    private final String string;

    public BString(String string) {
        this.string = string;
    }

    @Override
    public final int size() {
        return string.getBytes().length;
    }

    @Override
    public final <T> T toObject(Function<InputStream, T> fn) {
        try(InputStream stream = new ByteArrayInputStream(string.getBytes())) {
            return fn.apply(stream);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public final void read(Consumer<InputStream> fn) {
        try(InputStream stream = new ByteArrayInputStream(string.getBytes())) {
            fn.accept(stream);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
