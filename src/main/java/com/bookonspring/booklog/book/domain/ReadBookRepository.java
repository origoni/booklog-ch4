package com.bookonspring.booklog.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadBookRepository extends JpaRepository<ReadBook, ReadBookId> {

    List<ReadBook> findByUserId(String userId);
}