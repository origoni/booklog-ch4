package com.bookonspring.booklog.book.presentation;//package com.bookonspring.booklog.book.domain;

import com.bookonspring.booklog.book.domain.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/read")

@RequiredArgsConstructor
public class ReadBookController {

//    private final CategoryRepository categoryRepository;

    private final ReadBookService readBookService;


    @GetMapping(value = "/write")
    public String form(Model model, @RequestParam(value = "isbn") long isbn) {

        model.addAttribute("readBookCommand", new ReadBookCommand(isbn));
        model.addAttribute("readType", ReadType.values());

        return "read/form";
    }

    @ResponseBody
    @PostMapping(value = "/write")
    ReadBook write(ReadBookCommand readBookCommand) {

        ReadBook readBookResult = readBookService.saveReadBook(readBookCommand);

        return readBookResult;
    }


}
