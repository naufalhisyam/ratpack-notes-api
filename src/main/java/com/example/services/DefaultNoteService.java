package com.example.services;

import com.example.model.DefaultNoteDAO;
import com.example.model.Note;
import com.google.inject.Inject;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

public class DefaultNoteService implements NoteService {
    private final DefaultNoteDAO noteDAO;

    @Inject
    public DefaultNoteService(DefaultNoteDAO defaultNoteDAO) {
        this.noteDAO = defaultNoteDAO;
    }

    @Override
    public JSONObject getAllNotes() {
        JSONObject responseJSON = new JSONObject(); //generate response

        try { //fetch notes from DB
            List<Note> notesList = noteDAO.getAll();

            responseJSON.put("status", "success");
            JSONObject notesJSON = new JSONObject();
            notesJSON.put("notes", notesList);
            responseJSON.put("data", notesJSON);
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Failed to fetch notes from DB.");
            return responseJSON;
        }

        return responseJSON; //send notes as response JSON
    }

    @Override
    public JSONObject getNoteById(Long id) {
        JSONObject responseJSON = new JSONObject(); //generate response

        try { //fetch notes from DB
            Note note = noteDAO.getById(id);

            responseJSON.put("status", "success");
            JSONObject dataJSON = new JSONObject();
            dataJSON.put("note", new JSONObject(note));
            responseJSON.put("data", dataJSON);
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Note entry does not exist.");
            return responseJSON;
        }

        return responseJSON; //send notes as response JSON
    }

    @Override
    public JSONObject createNote(String body) {
        JSONObject data = new JSONObject(body); //Parse JSON request body
        JSONObject responseJSON = new JSONObject(); //generate response

        Note note = new Note(); //Create note object from parsed JSON
        try {
            if (data.getString("title").isBlank()
                    || data.getString("author").isBlank()
                    || data.getString("body").isBlank()) {
                throw new Exception();
            }

            note.setTitle(data.getString("title"));
            note.setAuthor(data.getString("author"));
            note.setBody(data.getString("body"));
            LocalDate currentTime = LocalDate.now();
            note.setCreatedAt(currentTime);
            note.setModifiedAt(currentTime);
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Request body is not valid.");
            return responseJSON;
        }

        try { //Save note to DB
            Long id = noteDAO.create(note);

            responseJSON.put("data", id.toString());
            responseJSON.put("status", "success");
            responseJSON.put("message", "Note successfully created.");
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Failed to save note to DB.");
            return responseJSON;
        }

        return responseJSON; //Send response
    }

    @Override
    public JSONObject updateNote(Long id, String body) {
        JSONObject data = new JSONObject(body); //Parse JSON request body
        JSONObject responseJSON = new JSONObject(); //generate response

        //Get note object from DB
        Note note = noteDAO.getById(id);
        try { //Update note object from parsed JSON
            boolean modified = false;
            if (data.has("title")) {
                if (data.getString("title").isBlank()) {throw new Exception();}
                note.setTitle(data.get("title").toString());
                modified = true;
            }
            if (data.has("author")) {
                if (data.getString("author").isBlank()) {throw new Exception();}
                note.setAuthor(data.get("author").toString());
                modified = true;
            }
            if (data.has("body")) {
                if (data.getString("body").isBlank()) {throw new Exception();}
                note.setBody(data.get("body").toString());
                modified = true;
            }
            if (modified) {
                LocalDate currentTime = LocalDate.now();
                note.setModifiedAt(currentTime);
            }
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Request body is not valid.");
            return responseJSON;
        }

        try { //Save note to DB
            noteDAO.update(note);

            responseJSON.put("status", "success");
            responseJSON.put("message", "Note successfully modified.");
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Failed to update note DB entry.");
            return responseJSON;
        }

        return responseJSON; //Send response
    }

    @Override
    public JSONObject deleteNote(Long id) {
        JSONObject responseJSON = new JSONObject(); //generate response

        try { //fetch notes from DB
            noteDAO.delete(id);

            responseJSON.put("status", "success");
            responseJSON.put("message", "Note successfully deleted.");
        } catch (Exception e) {
            responseJSON.put("status", "failed");
            responseJSON.put("message", "Note entry does not exist.");
            return responseJSON;
        }

        return responseJSON; //send notes as response JSON
    }
}
