package com.bookonspring.booklog.book.presentation;

import com.bookonspring.booklog.book.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryRepository categoryRepository;


    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    public List<Category> categoryList(@PageableDefault(sort = {"useCnt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent();
    }

    @RequestMapping(value = "/categorys/@{userId}", method = RequestMethod.GET)
    public List<Category> findCategoriesByUserId(@PathVariable String userId) {
        List<Category> categories = categoryRepository.findCategoriesByUserId(userId);
        return categories;
    }

    @RequestMapping(value = "/categorys/{isbn}", method = RequestMethod.GET)
    public List<Category> findCategoriesByBook(@PathVariable long isbn) {
        return categoryRepository.findCategoriesByBook(isbn);
    }

}