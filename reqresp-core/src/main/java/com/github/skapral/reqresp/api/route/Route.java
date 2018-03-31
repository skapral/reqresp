package com.github.skapral.reqresp.api.route;

import com.github.skapral.reqresp.api.handle.Handle;
import com.github.skapral.reqresp.api.request.Request;

import java.util.Optional;

public interface Route {
    Optional<Handle> match(Request req);
}
