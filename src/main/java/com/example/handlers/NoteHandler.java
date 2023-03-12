package com.example.handlers;

import com.google.inject.Inject;
import com.example.services.DefaultNoteService;
import org.json.JSONObject;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.http.Status;


public class NoteHandler implements Handler{
    private final DefaultNoteService noteService;

    @Inject
    public NoteHandler(DefaultNoteService defaultNoteService) {
        this.noteService = defaultNoteService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.byMethod(method -> method
                .get(() -> {
                    JSONObject response = noteService.getAllNotes();
                    ctx.getResponse().contentType("application/json")
                            .beforeSend(resp -> {
                                if (response.get("status").equals("success")) {
                                    resp.status(Status.OK);
                                } else {resp.status(Status.INTERNAL_SERVER_ERROR);}
                            })
                            .send(response.toString());
                })



                .post(() -> ctx.getRequest().getBody()
                        .then(body -> {
                            JSONObject response = noteService.createNote(body.getText());
                            ctx.getResponse().contentType("application/json");
                            if (response.get("status").equals("success")) {
                                ctx.getResponse().status(Status.CREATED);
                            } else {
                                ctx.getResponse().status(Status.INTERNAL_SERVER_ERROR);
                            }
                            ctx.getResponse().send(response.toString());
                        })
                )
        );
    }
}

