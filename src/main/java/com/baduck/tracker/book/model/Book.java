package com.baduck.tracker.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @SequenceGenerator(
            sequenceName = "bookId_sequence",
            allocationSize = 1,
            name = "bookId_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookId_sequence")
    private Long bookId;

    @NotNull(message = "title must be")
    private String title;

    @NotNull(message = "author must be")
    private String author;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL
    )
    private Set<Note> notes;

    @Max(5)
    private Integer rating = 0;

    public Book(Long bookId, String title, String author, Integer rating) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public Book(String title, String author, Integer rating) {
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
        for (Note n : notes) {
            n.setBook(this.bookId);
        }
    }
}
