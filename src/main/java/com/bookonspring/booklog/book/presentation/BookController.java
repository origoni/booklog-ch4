package com.bookonspring.booklog.book.presentation;

import com.bookonspring.booklog.book.domain.Book;
import com.bookonspring.booklog.book.domain.BookRepository;
import com.bookonspring.booklog.book.domain.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/book/form", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String addBook(@Valid Book book, Errors errors) {
        if (errors.hasErrors()) return "book/form";
        bookRepository.save(book);
        return "redirect:/book";
    }


    @RequestMapping(value = "/book/list", method = RequestMethod.GET)
    public String findBooks(Model model, @PageableDefault(sort = {"publishedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Book> bookPage = bookService.getBooks(pageable);
        model.addAttribute("bookPage", bookPage);
        return "book/list";
    }

    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET)
    public String findBook(Model model, @PathVariable long isbn) {
        Book book = bookService.getBook(isbn);
        model.addAttribute("book", book);
        return "book/book";
    }
}