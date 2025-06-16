package com.packtpub.library;

// Example implementation of BookSaveFormat for saving books to file
public class TextFileSaveFormat implements BookSaveFormat {
    public void save(Book book) {
        System.out.println("Saving book '" + book.getTitle() + "' to file.");
    }
}