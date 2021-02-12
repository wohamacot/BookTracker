package com.baduck.tracker.book.service;

import com.baduck.tracker.book.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {


    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {

        this.noteRepository = noteRepository;
    }

    private void checkNoteWithId(Long noteId) {
        boolean exists = noteRepository.existsById(noteId);
        if (!exists) {
            throw new IllegalStateException("NOTE WITH ID=" + noteId + " DOESN'T EXISTS");
        }
    }


    public void removeNote(Long noteId) {
        checkNoteWithId(noteId);
        noteRepository.deleteById(noteId);

    }

    public void editNote(Long noteId, String text) {

        checkNoteWithId(noteId);
        if (!text.isEmpty()) {

            Optional<Note> noteOptional = noteRepository.findById(noteId);
            noteOptional.ifPresent(note -> {
                note.setText(text);
                noteRepository.save(note);
            });

        } else {
            throw new IllegalStateException("EMPTY REQUEST FOR EDIT");
        }


    }
}
