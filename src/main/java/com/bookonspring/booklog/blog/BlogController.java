package com.bookonspring.booklog.blog;


import com.bookonspring.booklog.post.domain.entity.Post;
import com.bookonspring.booklog.post.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping({"/@{userId}", "/@{userId}/post/list"})
    public String list(Model model, @PathVariable String userId, Pageable pageable) {

        Page<Post> postPage = postRepository.findByUserId(pageable, userId);

        model.addAttribute("userId", userId);
        model.addAttribute("postPage", postPage);
        return "blog/list";
    }

    @RequestMapping("/@{userId}/post/{id}")
    public String view(Model model, @PathVariable String userId, @PathVariable int id) {
        Post post = postRepository.getOne(id);
        log.warn("post.getPostTagList()={}", post.getPostTagList());

        model.addAttribute("userId", userId);
        model.addAttribute("post", post);
        return "blog/post";
    }
}
