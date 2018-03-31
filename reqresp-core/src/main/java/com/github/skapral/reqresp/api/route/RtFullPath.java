package com.github.skapral.reqresp.api.route;

import com.github.skapral.reqresp.api.handle.Handle;
import com.github.skapral.reqresp.api.request.Request;

import java.util.Optional;

public class RtFullPath implements Route {
    private final String path;
    private final Handle handle;

    public RtFullPath(String path, Handle handle) {
        this.path = path;
        this.handle = handle;
    }

    @Override
    public final Optional<Handle> match(Request req) {
        if(req.path().equals(path)) {
            return Optional.of(handle);
        } else {
            return Optional.empty();
        }
    }
}
