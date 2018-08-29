package com.bookonspring.booklog.book.presentation;

import com.bookonspring.booklog.book.domain.Book;
import com.bookonspring.booklog.book.domain.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> findBooks(@PageableDefault(sort = {"publishedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<Book> bookList = bookService.getBooks(pageable).getContent();
        return bookList;
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    public Book findBook(@PathVariable long isbn) {
        Book book = bookService.getBook(isbn);
        return book;
    }
}