package com.baduck.tracker.book.service;

import com.baduck.tracker.book.model.Book;
import com.baduck.tracker.book.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public BookService(BookRepository bookRepository, NoteRepository noteRepository) {
        this.bookRepository = bookRepository;
        this.noteRepository = noteRepository;
    }


    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByTitle(book.getTitle());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("BOOK "
                    + book.getTitle() + " ALREADY EXISTS");
        }
        bookRepository.save(book);

    }

    public void deleteBook(Long bookId) {
        checkBookWithId(bookId);
        bookRepository.findById(bookId)
                .ifPresent(b -> {
                            System.out.println("BOOK '" + b.getTitle()
                                    + "' BY '" + b.getAuthor() + "' WAS REMOVE");
                        }
                );

        bookRepository.deleteById(bookId);
    }

    private void checkBookWithId(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("BOOK WITH ID=" + bookId + " DOESN'T EXISTS");
        }
    }

    public void changeRating(Long bookId, Integer rating) {
        checkBookWithId(bookId);
        bookRepository.findById(bookId)
                .ifPresent(book -> {
                            book.setRating(rating);
                            bookRepository.save(book);
                            System.out.println("RATING OF " + book.getTitle()
                                    + " EQUALS " + rating + " NOW");
                        }
                );
    }

    public void addNote(Long bookId, Note note) {
        checkBookWithId(bookId);

        bookRepository.findById(bookId)
                .ifPresent(book -> {
                    note.setBook(book.getBookId());
                    noteRepository.save(note);
                    Set<Note> notes = book.getNotes();
                    notes.add(note);
                    bookRepository.save(book);
                    System.out.println("NOTE " + note.getNoteId() + " WAS ADD FOR BOOK "
                            + book.getTitle());
                });

    }


    public Set<Book> findBook(String text) {
        Set<Book> foundBooks = new HashSet<>();

        bookRepository.findAll().forEach(book -> {
            if (book.getTitle().contains(text)) {
                foundBooks.add(book);
            }
        });
        return foundBooks;
    }
}
