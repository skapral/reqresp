package com.github.skapral.reqresp.se.server;

import com.github.skapral.reqresp.api.enums.Method;
import com.github.skapral.reqresp.api.handle.Handle;
import com.github.skapral.reqresp.api.header.HdrExplicit;
import com.github.skapral.reqresp.api.header.Header;
import com.github.skapral.reqresp.api.request.ReqExplicit;
import com.github.skapral.reqresp.api.request.Request;
import com.github.skapral.reqresp.api.response.Response;
import com.github.skapral.reqresp.api.server.Server;
import com.github.skapral.reqresp.se.bytes.BytesSE;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import oo.atom.anno.NotAtom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.stream.Collectors;

public class ServerSE implements Server {
    private final int port;

    public ServerSE(int port) {
        this.port = port;
    }

    @Override
    public final StopTrigger start(Handle handle) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new ServerSE.HandleAdapter(handle));
            server.setExecutor(null);
            server.start();
            return new SEStopTrigger(server);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @NotAtom
    private static final class HandleAdapter implements HttpHandler {
        private final Handle handle;

        public HandleAdapter(Handle handle) {
            this.handle = handle;
        }

        @Override
        public final void handle(HttpExchange httpExchange) throws IOException {
            try {
                final Iterable<Header> headers = httpExchange.getRequestHeaders()
                    .entrySet().stream()
                    .map(es -> new HdrExplicit(es.getKey(), es.getValue().get(0)))
                    .collect(Collectors.toList());
                final String path = httpExchange.getRequestURI().getPath();
                final Method method = Method.valueOf(httpExchange.getRequestMethod().toUpperCase());
                final Request request = new ReqExplicit(
                    headers,
                    path,
                    new BytesSE(httpExchange),
                    method
                );
                final Response response = handle.exchange(request);
                httpExchange.sendResponseHeaders(response.status(), 0);
                response.body().read(stream -> {
                    try (OutputStream ostream = httpExchange.getResponseBody()) {
                        IOUtils.copy(stream, ostream);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                response.headers().forEach(header -> httpExchange.getResponseHeaders().add(header.name(), header.value()));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    private static final class SEStopTrigger implements Server.StopTrigger {
        private final HttpServer server;

        public SEStopTrigger(HttpServer server) {
            this.server = server;
        }

        @Override
        public final void stop() {
            server.stop(0);
        }
    }


    @NotAtom
    private static final class IOUtils {
        public static final void copy(InputStream in, OutputStream out) {
            try {
                byte[] buffer = new byte[1024];
                int len = in.read(buffer);
                while (len != -1) {
                    out.write(buffer, 0, len);
                    len = in.read(buffer);
                }
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
