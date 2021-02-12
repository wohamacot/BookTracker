package com.baduck.tracker.book.controller;

import com.baduck.tracker.book.model.Book;
import com.baduck.tracker.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api.bt")
public class BookController {

    public final BookService service;

    @Autowired
    public BookController(BookService service) {

        this.service = service;
    }

    @GetMapping(path = "books")
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @PostMapping(path = "books")
    public void addBook(@RequestBody Book book) {

        service.addNewBook(book);
    }

    @DeleteMapping(path = "books/{bookId}")
    public void removeBook(@PathVariable("bookId") Long bookId) {
        service.deleteBook(bookId);
    }

    @PutMapping(path = "books/{bookId}", params = "rating")
    public void rateBook(@PathVariable("bookId") Long bookId,
                         @RequestParam(name = "rating") Integer rating) {
        service.changeRating(bookId, rating);
    }


}
