package com.github.skapral.reqresp.api.handle;

import com.github.skapral.reqresp.api.request.Request;
import com.github.skapral.reqresp.api.response.Response;

public interface Handle {

    Response exchange(Request request);
}
