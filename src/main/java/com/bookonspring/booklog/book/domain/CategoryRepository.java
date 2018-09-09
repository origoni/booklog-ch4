package com.bookonspring.booklog.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value = "SELECT " +
            "    c.name, COUNT(c.name) as use_cnt " +
            "FROM " +
            "    READ_BOOK_CATEGORY c " +
            "WHERE " +
            "    c.USER_ID = :userId " +
            "GROUP BY " +
            "    c.name " +
            "ORDER BY " +
            "    COUNT(c.name) DESC "
            , nativeQuery = true)
    List<Category> findCategoriesByUserId(@Param("userId") String userId);

    @Query(value = "SELECT " +
            "    c.name, COUNT(c.name) as use_cnt " +
            "FROM " +
            "    READ_BOOK_CATEGORY c " +
            "WHERE " +
            "    c.book = ?1 " +
            "GROUP BY " +
            "    c.name " +
            "ORDER BY " +
            "    COUNT(c.name) DESC "
            , nativeQuery = true)
    List<Category> findCategoriesByBook(Long isbn);
}