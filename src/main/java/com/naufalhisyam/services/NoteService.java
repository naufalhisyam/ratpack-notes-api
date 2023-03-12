package com.naufalhisyam.services;

import org.json.JSONObject;

public interface NoteService {
    JSONObject getAllNotes();
    JSONObject getNoteById(Long id);
    JSONObject createNote(String body);
    JSONObject updateNote(Long id, String body);
    JSONObject deleteNote(Long id);

}
