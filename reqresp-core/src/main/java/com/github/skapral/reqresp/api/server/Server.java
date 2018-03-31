package com.github.skapral.reqresp.api.server;

import com.github.skapral.reqresp.api.handle.Handle;

public interface Server {
    interface StopTrigger {
        void stop();
    }

    StopTrigger start(Handle handle);
}
