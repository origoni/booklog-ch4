package com.bookonspring.booklog.book.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadBookCommand {
    String userId;

    //    long isbn;

    public ReadBookCommand(long isbn) {
        this.book = new Book(isbn);
    }

    Book book;


    ReadType readType;

    String tags = "";

}
