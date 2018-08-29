package com.bookonspring.booklog.post.presentation;

import com.bookonspring.booklog.post.domain.PostCommand;
import com.bookonspring.booklog.post.domain.entity.Post;
import com.bookonspring.booklog.post.domain.repository.PostRepository;
import com.bookonspring.booklog.post.domain.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    private PostRepository postRepository;


    @GetMapping(value = "/write")
    public String form(Model model, @RequestParam(value = "isbn") long isbn) {

        model.addAttribute("postCommand", postService.getForm(isbn));

        return "post/form";
    }

    @PostMapping(value = "/write")
    public String write(@Valid PostCommand postCommand, Errors errors) {
        if (errors.hasErrors()) {
            return "post/form";
        }

        Post post = postService.write(postCommand);

        return "redirect:/@" + post.getUserId() + "/post/" + post.getId();
    }


    @RequestMapping("/list")
    public String list(Model model, Pageable pageable) {

        Page<Post> postPage = postRepository.findAll(pageable);

        model.addAttribute("postPage", postPage);

        return "post/list";
    }


    @GetMapping(value = "/{id}/edit")
    public String editor(Model model, @PathVariable int id) {
        Post post = postRepository.getOne(id);
        model.addAttribute("postCommand", new PostCommand(post));
        return "blog/form";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(@Valid PostCommand postCommand, Errors errors) {
        if (errors.hasErrors()) {
            return "blog/form";
        }
        Post post = postService.edit(postCommand);

        return "redirect:/@" + post.getUserId() + "/post/" + post.getId();
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        String userId = postRepository.getOne(id).getUserId();
        postService.del(id);

        return "redirect:/@" + userId;
    }
}
