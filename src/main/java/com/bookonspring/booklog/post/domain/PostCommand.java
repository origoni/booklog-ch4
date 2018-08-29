package com.bookonspring.booklog.post.domain;

import com.bookonspring.booklog.book.domain.Book;
import com.bookonspring.booklog.post.domain.entity.Post;
import com.bookonspring.booklog.tag.domain.entity.PostTag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@Slf4j
@NoArgsConstructor
public class PostCommand {

    int id;

    @NotNull
    @Size(min = 1, max = 255)
    String subject;

    Book book;

    @NotNull
    @Size(min = 5, max = 1000)
    String content;

    @Size(min = 5, max = 16)
    String userId;


    ZonedDateTime createdAt;

    String tags = "";

    public PostCommand(Post post) {
        BeanUtils.copyProperties(post, this);

        for (PostTag postTag : post.getPostTagList()) {
            log.debug("postTag = {}", postTag.getTag());
            tags = tags + postTag.getTag().getName() + " ";
        }
    }

    public PostCommand(Post post, String tags) {
        BeanUtils.copyProperties(post, this);

        this.tags = tags;
    }

}
