package com.naufalhisyam.handlers;

import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

public class LoggingHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        System.out.println("Received: " + ctx.getRequest().getUri());
        ctx.next();
    }
}
