package com.github.skapral.reqresp.se.demo;

import com.github.skapral.reqresp.api.bytes.BString;
import com.github.skapral.reqresp.api.handle.HRouted;
import com.github.skapral.reqresp.api.handle.Handle;
import com.github.skapral.reqresp.api.request.Request;
import com.github.skapral.reqresp.api.response.RespExplicit;
import com.github.skapral.reqresp.api.response.Response;
import com.github.skapral.reqresp.api.route.RtFullPath;
import com.github.skapral.reqresp.se.server.ServerSE;
import oo.atom.anno.NotAtom;

import java.util.Arrays;
import java.util.Collections;




class TestHandle implements Handle {
    @Override
    public final Response exchange(Request request) {
        return new RespExplicit(
            200,
            new BString("echo"),
            Collections.emptyList()
        );
    }
}

@NotAtom
public class Main {
    public static void main(String[] args) throws Exception {
        new ServerSE(
            8000
        ).start(
            new HRouted(
                Arrays.asList(
                    new RtFullPath(
                    "/test", new TestHandle()
                    )
                )
            )
        );
    }
}
