package com.bookonspring.booklog.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Embeddable
public class ReadBookId implements Serializable {

    String userId;

    long book;

//    @ManyToOne
//    @JoinColumn(name = "isbn")
//    Book book;
}
