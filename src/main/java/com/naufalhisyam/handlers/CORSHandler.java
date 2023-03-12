package com.naufalhisyam.handlers;

import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.http.MutableHeaders;

public class CORSHandler implements Handler {
    @Override
    public void handle(Context context) {
        MutableHeaders headers = context.getResponse().getHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
        headers.set("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        context.next();
    }
}