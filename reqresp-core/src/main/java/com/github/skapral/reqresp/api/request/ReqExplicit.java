package com.github.skapral.reqresp.api.request;

import com.github.skapral.reqresp.api.bytes.Bytes;
import com.github.skapral.reqresp.api.enums.Method;
import com.github.skapral.reqresp.api.header.Header;

public class ReqExplicit implements Request {
    private final Iterable<Header> headers;
    private final String path;
    private final Bytes body;
    private final Method method;

    public ReqExplicit(Iterable<Header> headers, String path, Bytes body, Method method) {
        this.headers = headers;
        this.path = path;
        this.body = body;
        this.method = method;
    }

    @Override
    public final Iterable<Header> headers() {
        return headers;
    }

    @Override
    public final String path() {
        return path;
    }

    @Override
    public final Method method() {
        return method;
    }

    @Override
    public final Bytes body() {
        return body;
    }
}
