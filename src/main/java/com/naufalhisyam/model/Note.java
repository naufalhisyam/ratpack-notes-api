package com.naufalhisyam.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "NOTES")
public class Note {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(
            name = "notes_id_sequence",
            sequenceName = "notes_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notes_id_sequence"
    )
    private Long id;

    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "BODY")
    private String body;
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;
    @Column(name = "MODIFIED_AT")
    private LocalDate modifiedAt;

    public Note(Long id, String title, String tags, String body, LocalDate createdAt, LocalDate modifiedAt) {
        this.id = id;
        this.title = title;
        this.author = tags;
        this.body = body;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Note() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(author, note.author) && Objects.equals(body, note.body) && Objects.equals(createdAt, note.createdAt) && Objects.equals(modifiedAt, note.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, body, createdAt, modifiedAt);
    }
}
