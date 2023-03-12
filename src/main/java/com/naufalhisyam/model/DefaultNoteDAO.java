package com.naufalhisyam.model;

import com.naufalhisyam.utils.HibernateUtility;
import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class DefaultNoteDAO implements NoteDAO{
    private final SessionFactory sessionFactory;

    @Inject
    public DefaultNoteDAO() {
        this.sessionFactory = HibernateUtility.getSessionFactory();
    }

    @Override
    public List<Note> getAll() {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        SelectionQuery<Note> query = s.createSelectionQuery("SELECT n FROM Note n", Note.class);
        List<Note> notesList = query.getResultList();
        s.getTransaction().commit();
        s.close();

        return notesList;
    }

    @Override
    public Note getById(Long id) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        Note note = s.get(Note.class, id);
        s.getTransaction().commit();
        s.close();

        return note;
    }

    @Override
    public Long create(Note note) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        s.persist(note);
        s.getTransaction().commit();
        Long id = note.getId();
        s.close();

        return id;
    }

    @Override
    public void update(Note note) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        s.merge(note);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void delete(Long id) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        Note note = s.get(Note.class, id);
        s.remove(note);
        s.getTransaction().commit();
        s.close();
    }
}
