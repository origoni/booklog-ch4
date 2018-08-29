package com.bookonspring.booklog.post.domain.entity;

import com.bookonspring.booklog.book.domain.Book;
import com.bookonspring.booklog.post.domain.PostCommand;
import com.bookonspring.booklog.tag.domain.entity.PostTag;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "isbn")
    Book book;

    String subject;


    @NotNull
    @Column(length = 1000)
    String content;

    @Column(length = 16)
    String userId;


    @CreationTimestamp
    ZonedDateTime createdAt;

    @UpdateTimestamp
    ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<PostTag> postTagList;

    public Post() {
    }

    public Post(PostCommand postCommand) {
        BeanUtils.copyProperties(postCommand, this);
    }

    public Post(Long isbn, String subject, String content, String userId) {

        this.book = new Book();
        this.book.setIsbn(isbn);

        this.subject = subject;
        this.content = content;

        this.userId = userId;
    }
}
