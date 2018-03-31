package com.github.skapral.reqresp.api.request;

import com.github.skapral.reqresp.api.bytes.Bytes;
import com.github.skapral.reqresp.api.enums.Method;
import com.github.skapral.reqresp.api.header.Header;

public interface Request {
    Iterable<Header> headers();
    String path();
    Method method();
    Bytes body();
}
