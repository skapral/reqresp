package com.github.skapral.reqresp.se.bytes;

import com.github.skapral.reqresp.api.bytes.Bytes;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class BytesSE implements Bytes {
    private final HttpExchange exchange;

    public BytesSE(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public final <T> T toObject(Function<InputStream, T> fn) {
        try(InputStream stream = exchange.getRequestBody()) {
            return fn.apply(stream);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public final void read(Consumer<InputStream> fn) {
        try(InputStream stream = exchange.getRequestBody()) {
            fn.accept(stream);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public final int size() {
        return Integer.parseInt(exchange.getRequestHeaders()
                .getFirst("Content-Size"));

    }
}
