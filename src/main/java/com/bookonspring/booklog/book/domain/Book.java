package com.bookonspring.booklog.book.domain;

import com.bookonspring.booklog.tag.domain.TagCount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "books")
@NoArgsConstructor
public class Book {

    @NotNull
    @Id
    long isbn;

    @Transient
    List<TagCount> tags;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    String title;

    @NotNull
    @Size(min = 1, max = 255)
    @Column
    String authors;

    @NotNull
    @Size(min = 1, max = 255)
    String publisher;

    @Column(name = "img_url", length = 500)
    String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate publishedDate;


    public Book(long isbn){
        this.isbn = isbn;
    }

    public Book(long isbn, String title, String authors, String publisher, String imageUrl, LocalDate publishedDate) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
        this.publishedDate = publishedDate;
    }
}
