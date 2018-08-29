package com.bookonspring.booklog.post.domain.service;

import com.bookonspring.booklog.book.domain.Book;
import com.bookonspring.booklog.book.domain.BookRepository;
import com.bookonspring.booklog.post.domain.PostCommand;
import com.bookonspring.booklog.post.domain.entity.Post;
import com.bookonspring.booklog.post.domain.repository.PostRepository;
import com.bookonspring.booklog.tag.domain.service.PostTagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    private BookRepository bookRepository;

    private PostTagService postTagService;


    public PostCommand getForm(long isbn) {
        Book book = bookRepository.getOne(isbn);

        System.out.println("book=" + book);

        PostCommand postCommand = new PostCommand();
        postCommand.setBook(book);

        return postCommand;
    }

    public Post write(PostCommand postCommand) {

        postCommand.setCreatedAt(ZonedDateTime.now());

        Post post = postRepository.save(new Post(postCommand));

        postCommand.setId(post.getId());

        postTagService.insertPostTag(postCommand);

//        List<PostTag> postTagList = postTagService.insertPostTag(postCommand);
//        post.setPostTagList(postTagList);

        return post;
    }

    public Post edit(PostCommand postCommand) {
        Post post = postRepository.save(new Post(postCommand));
        postTagService.updatePostTag(postCommand);
        return post;
    }

    public void del(int id) {
        postTagService.deletePostTagByPostId(id);
        postRepository.deleteById(id);
    }

}
