package com.bookonspring.booklog.book.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Entity
@IdClass(ReadBookId.class)
@NoArgsConstructor
public class ReadBook {

//    @EmbeddedId
//    private ReadBookId id;

    @Id
    String userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "isbn")
    Book book;


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "read_book_category",
            joinColumns = {@JoinColumn(name = "book"), @JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "name"))
    private Set<Category> categories;


    @CreationTimestamp
    ZonedDateTime createdAt;

    @UpdateTimestamp
    ZonedDateTime updatedAt;


//    LocalDate startDate;
//
//    LocalDate endDate;


    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    ReadType readType;

//    public enum ReadType {
//        BEFORE,
//        READING,
//        DONE
//    }
}
