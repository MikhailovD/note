package ru.mdg.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notation {
    //private IntegerProperty noteId;
    private ObjectProperty<LocalDate> date;
    private StringProperty text;

    public Notation(LocalDate date, String text) {
        setDate(date);
        setText(text);
    }
    /*
    public int getNoteId() {
        return noteId.get();
    }

    public void setNoteId(int noteId) {
        this.noteId.set(noteId);
    }

    public IntegerProperty noteIdProperty() {
        return noteId;
    }
    */

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate d) {
        this.date.set(d);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public StringProperty textProperty() {
        return text;
    }
}
