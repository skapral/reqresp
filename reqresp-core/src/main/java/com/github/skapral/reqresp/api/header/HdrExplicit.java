package com.github.skapral.reqresp.api.header;

public class HdrExplicit implements Header {
    private final String key;
    private final String value;

    public HdrExplicit(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final String name() {
        return key;
    }

    @Override
    public final String value() {
        return value;
    }
}
