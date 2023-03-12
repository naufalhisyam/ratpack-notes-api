package com.naufalhisyam;

import com.naufalhisyam.handlers.NoteByIdHandler;
import com.naufalhisyam.handlers.NoteHandler;
import com.naufalhisyam.handlers.CORSHandler;
import ratpack.core.server.RatpackServer;
import ratpack.guice.Guice;

public class Main {
 public static void main(String... args) throws Exception {
   RatpackServer.start(spec -> spec
           .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(NoteModule.class)))
           .handlers(handler -> handler
                   .all(CORSHandler.class)
                   .path("notes", NoteHandler.class)
                   .path("notes/:id", NoteByIdHandler.class)
     )
   );
 }
}