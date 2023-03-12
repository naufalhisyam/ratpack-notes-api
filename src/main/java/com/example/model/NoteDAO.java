package com.example.model;

import java.util.List;

public interface NoteDAO {
    List<Note> getAll();
    Note getById(Long id);
    Long create(Note note);
    void update(Note note);
    void delete(Long id);
}
