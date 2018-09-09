package com.bookonspring.booklog.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.StringTokenizer;

@Slf4j
@Service
@RequiredArgsConstructor

public class ReadBookService {

    private final CategoryRepository categoryRepository;


    private final ReadBookRepository readBookRepository;


    public ReadBook saveReadBook(ReadBookCommand readBookCommand) {
        log.warn("readBookCommand={}", readBookCommand);
        ReadBook readBook = new ReadBook();
        readBook.setBook(readBookCommand.getBook());
        readBook.setUserId(readBookCommand.getUserId());
        readBook.setReadType(readBookCommand.getReadType());

        HashSet<Category> categories = tagNamesToHashSet(readBookCommand.getTags());

        readBook.setCategories(categories);
        return readBookRepository.save(readBook);
    }

    private HashSet<Category> tagNamesToHashSet(String postTagNames) {

        StringTokenizer tokenTag = new StringTokenizer(postTagNames.trim().replaceAll("[ ]+", " "), " ");

        HashSet<Category> hashSet = new HashSet<>();

        while (tokenTag.hasMoreElements()) {
            String tag = tokenTag.nextToken();
            Optional<Category> category = categoryRepository.findById(tag);
            if (category.isPresent()) {
                Category c = category.get();
                c.increaseUseCount();

                hashSet.add(c);
            } else {
                hashSet.add(new Category(tag, 1));
            }

            if (hashSet.size() >= 10) {
                break;
            }
        }

        return hashSet;
    }
}
