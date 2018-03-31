package com.github.skapral.reqresp.api.response;

import com.github.skapral.reqresp.api.bytes.Bytes;
import com.github.skapral.reqresp.api.header.Header;

public interface Response {
    int status();
    Bytes body();
    Iterable<Header> headers();
}
