package com.baduck.tracker.book.controller;

import com.baduck.tracker.book.model.Note;
import com.baduck.tracker.book.service.BookService;
import com.baduck.tracker.book.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api.bt")
public class NoteController {

    public final BookService bookService;
    public final NoteService noteService;

    @Autowired
    public NoteController(BookService bookService, NoteService noteService) {
        this.bookService = bookService;
        this.noteService = noteService;
    }


    @PutMapping(path = "books/notes/{bookId}")
    public void addNoteToBook(@PathVariable("bookId") Long bookId,
                              @RequestBody Note note) {
        bookService.addNote(bookId, note);

    }

    @DeleteMapping(path = "books/notes/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId) {
        noteService.removeNote(noteId);
    }

    @PutMapping(path = "books/notes/edit/{noteId}")
    public void editNote(@PathVariable("noteId") Long noteId,
                         @RequestBody String text) {

        noteService.editNote(noteId, text);
    }

}
