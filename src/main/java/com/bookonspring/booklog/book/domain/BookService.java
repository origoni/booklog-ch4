package com.bookonspring.booklog.book.domain;

import com.bookonspring.booklog.tag.domain.repository.PostTagRepository;
import com.bookonspring.booklog.tag.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PostTagRepository postTagRepository;

    @Autowired
    TagService tagService;

    public Page<Book> getBooks(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);
        bookPage.get().forEach(book -> book.setTags(postTagRepository.getBookTagList(book.getIsbn())));
        return bookPage;
    }

    public Book getBook(long isbn) {
        Book book = bookRepository.getOne(isbn);
        book.setTags(tagService.getBookTagList(isbn));
        return book;
    }
}

