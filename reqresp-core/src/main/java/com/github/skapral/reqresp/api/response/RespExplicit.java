package com.github.skapral.reqresp.api.response;

import com.github.skapral.reqresp.api.bytes.Bytes;
import com.github.skapral.reqresp.api.header.Header;

public class RespExplicit implements Response {
    private final int status;
    private final Bytes body;
    private final Iterable<Header> headers;

    public RespExplicit(int status, Bytes body, Iterable<Header> headers) {
        this.status = status;
        this.body = body;
        this.headers = headers;
    }

    @Override
    public final int status() {
        return status;
    }

    @Override
    public final Bytes body() {
        return body;
    }

    @Override
    public final Iterable<Header> headers() {
        return headers;
    }
}
