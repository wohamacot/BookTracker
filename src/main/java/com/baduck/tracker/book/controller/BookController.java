package com.baduck.tracker.book.controller;

import com.baduck.tracker.book.model.Book;
import com.baduck.tracker.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api.bt/books")
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

    @GetMapping(path = "search")
    public Set<Book> search(@RequestBody String text) {
        Set<Book> book = service.findBook(text);
        if (!book.isEmpty()) {
            return book;
        } else {
            return null;
        }
    }


}
