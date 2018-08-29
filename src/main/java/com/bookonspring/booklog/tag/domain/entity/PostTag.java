package com.bookonspring.booklog.tag.domain.entity;

import com.bookonspring.booklog.post.domain.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@ToString(exclude = "post")
@Entity
@NoArgsConstructor
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Post post;

    private int postId;

    private long isbn;

    @ManyToOne
    @JoinColumn(name = "tagId", insertable = false, updatable = false)
    private Tag tag;

    private int tagId;

    private Date regDate;


    public PostTag(long isbn, int postId, int tagId) {
        this.isbn = isbn;
        this.postId = postId;
        this.tagId = tagId;
        this.regDate = new Date();
    }

}
