package com.naufalhisyam.handlers;

import com.naufalhisyam.services.DefaultNoteService;
import com.google.inject.Inject;
import org.json.JSONObject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.http.Status;

public class NoteByIdHandler implements Handler {
    private final DefaultNoteService defaultNoteService;

    @Inject
    public NoteByIdHandler(DefaultNoteService defaultNoteService) {
        this.defaultNoteService = defaultNoteService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.byMethod(method -> method
                .get(() -> {
                    Long id = ctx.getPathTokens().asLong("id");
                    JSONObject response = defaultNoteService.getNoteById(id);
                    ctx.getResponse().contentType("application/json")
                            .beforeSend(resp -> {
                                if (response.get("status").equals("success")) {
                                    resp.status(Status.OK);
                                } else {resp.status(Status.NOT_FOUND);}
                            })
                            .send(response.toString());
                })

                .put(() -> ctx.getRequest().getBody()
                        .then(body -> {
                            Long id = ctx.getPathTokens().asLong("id");
                            JSONObject response = defaultNoteService.updateNote(id, body.getText());
                            ctx.getResponse().contentType("application/json");
                            if (response.get("status").equals("success")) {
                                ctx.getResponse().status(Status.OK);
                            } else {
                                ctx.getResponse().status(Status.INTERNAL_SERVER_ERROR);
                            }
                            ctx.getResponse().send(response.toString());
                        })
                )

                .delete(() -> {
                    Long id = ctx.getPathTokens().asLong("id");
                    JSONObject response = defaultNoteService.deleteNote(id);
                    ctx.getResponse().contentType("application/json")
                            .beforeSend(resp -> {
                                if (response.get("status").equals("success")) {
                                    resp.status(Status.OK);
                                } else {resp.status(Status.NOT_FOUND);}
                            })
                            .send(response.toString());
                })

        );


    }
}
