package com.baduck.tracker.book.controller;

import com.baduck.tracker.book.model.Book;
import com.baduck.tracker.book.model.Note;
import com.baduck.tracker.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/lib")
public class BookController {

    public final BookService service;

    @Autowired
    public BookController(BookService service) {

        this.service = service;
    }

    @GetMapping
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {

        service.addNewBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void removeBook(@PathVariable("bookId") Long bookId) {
        service.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}", params = "rating")
    public void rateBook(@PathVariable("bookId") Long bookId,
                         @RequestParam(name = "rating") Integer rating) {
        service.changeRating(bookId, rating);
    }

    @PutMapping(path = "note/{bookId}")
    public void addNoteToBook(@PathVariable("bookId") Long bookId,
                              @RequestBody Note note) {
        service.addNote(bookId, note);

    }

    @DeleteMapping(path = "note/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId){
        service.removeNote(noteId);
    }

}
