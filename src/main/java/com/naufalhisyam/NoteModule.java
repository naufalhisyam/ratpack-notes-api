package com.naufalhisyam;

import com.naufalhisyam.handlers.CORSHandler;
import com.naufalhisyam.handlers.LoggingHandler;
import com.naufalhisyam.handlers.NoteByIdHandler;
import com.naufalhisyam.handlers.NoteHandler;
import com.naufalhisyam.model.DefaultNoteDAO;
import com.naufalhisyam.model.NoteDAO;
import com.naufalhisyam.services.NoteService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.naufalhisyam.services.DefaultNoteService;
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
