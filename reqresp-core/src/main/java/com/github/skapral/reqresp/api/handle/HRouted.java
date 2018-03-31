package com.github.skapral.reqresp.api.handle;

import com.github.skapral.reqresp.api.request.Request;
import com.github.skapral.reqresp.api.response.Response;
import com.github.skapral.reqresp.api.route.Route;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class HRouted implements Handle {
    private final Iterable<Route> routes;

    public HRouted(Iterable<Route> routes) {
        this.routes = routes;
    }

    @Override
    public final Response exchange(Request request) {
        return StreamSupport.stream(routes.spliterator(), false)
            .map(route -> route.match(request))
            .filter(Optional::isPresent)
            .findFirst()
            .map(Optional::get)
            .map(route -> route.exchange(request))
            .orElseThrow(() -> new RuntimeException("No Route found for " + request.path()));
    }
}
