package com.bookonspring.booklog.commnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

    @Autowired
    CommentDao commentDao;

    @GetMapping
    public List<Comment> list(@RequestParam(value = "postId", required = true) int postId) {

        return commentDao.findByPostId(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment save(@RequestParam(value = "postId", required = true) int postId,
                        @RequestParam(value = "content", required = true) String content,
                        @RequestParam(value = "userId", required = true) String userId) {

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setCreatedAt(ZonedDateTime.now());

        return commentDao.save(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam(value = "postId", required = true) int postId, @PathVariable int id) {

        commentDao.deleteById(id);
    }
}
