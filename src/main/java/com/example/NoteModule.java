package com.example;

import com.example.handlers.CORSHandler;
import com.example.handlers.LoggingHandler;
import com.example.handlers.NoteByIdHandler;
import com.example.handlers.NoteHandler;
import com.example.model.DefaultNoteDAO;
import com.example.model.NoteDAO;
import com.example.services.NoteService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.example.services.DefaultNoteService;
import com.google.inject.multibindings.Multibinder;
import ratpack.core.handling.HandlerDecorator;

public class NoteModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NoteDAO.class).to(DefaultNoteDAO.class).in(Scopes.SINGLETON);
        bind(NoteService.class).to(DefaultNoteService.class).in(Scopes.SINGLETON);
        bind(CORSHandler.class).in(Scopes.SINGLETON);
        bind(NoteHandler.class).in(Scopes.SINGLETON);
        bind(NoteByIdHandler.class).in(Scopes.SINGLETON);
        Multibinder.newSetBinder(binder(), HandlerDecorator.class).addBinding()
                .toInstance(HandlerDecorator.prepend(new LoggingHandler()));
    }
}
