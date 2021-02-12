package com.baduck.tracker.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "note")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Note {
    @Id
    @SequenceGenerator(
            sequenceName = "noteId_sequence",
            allocationSize = 1,
            name = "noteId_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteId_sequence")
    private Long noteId;
    @NotNull
    private String text;

    private Long book;


    public Note(Long noteId, @NotNull String text) {
        this.noteId = noteId;
        this.text = text;
    }

    public Note(@NotNull String text) {
        this.text = text;
    }
}
