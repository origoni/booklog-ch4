package com.bookonspring.booklog.book.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

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

    public enum ReadType {
        BEFORE,
        READING,
        DONE
    }
}
