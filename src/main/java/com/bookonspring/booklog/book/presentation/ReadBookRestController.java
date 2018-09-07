package com.bookonspring.booklog.book.presentation;

import com.bookonspring.booklog.book.domain.ReadBook;
import com.bookonspring.booklog.book.domain.ReadBookId;
import com.bookonspring.booklog.book.domain.ReadBookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class ReadBookRestController {

    ReadBookRepository readBookRepository;

    @RequestMapping("/@{userId}/books/{isbn}")
    public Optional<ReadBook> view(@PathVariable String userId, @PathVariable long isbn) {


        return readBookRepository.findById(new ReadBookId(userId, isbn));
//        return readBookRepository.findById(new ReadBookId(userId, new Book(isbn)));
    }

    @RequestMapping("/@{userId}/books")
    public List<ReadBook> view(@PathVariable String userId) {


        return readBookRepository.findByUserId(userId);
    }

}