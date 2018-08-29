package com.bookonspring.booklog.post.presentation;

import com.bookonspring.booklog.post.domain.entity.Post;
import com.bookonspring.booklog.post.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{isbn}")
    public List<Post> listByIsbn(@PathVariable Long isbn) {
        return postRepository.findByBookIsbn(isbn);
    }


    @GetMapping
    public List<Post> list() {
        return postRepository.findAll();
    }

}
