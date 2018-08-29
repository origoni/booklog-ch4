package com.bookonspring.booklog.tag.domain.repository;

import com.bookonspring.booklog.tag.domain.TagCount;
import com.bookonspring.booklog.tag.domain.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {

//	List<PostTag> findByTagName(String tagName);

    List<PostTag> findByPostId(int postId);

    List<PostTag> findByIsbn(long isbn);

    @Query("SELECT " +
            "    new com.bookonspring.booklog.tag.domain.TagCount(t.tag.name, COUNT(t)) " +
            "FROM " +
            "    PostTag t " +
            "WHERE " +
            "    t.post.userId = :userId " +
            "GROUP BY " +
            "    t.tagId " +
            "ORDER BY " +
            "    COUNT(t) DESC "
    )
    List<TagCount> getUserTagList(@Param("userId") String userId);

    @Query("SELECT " +
            "    new com.bookonspring.booklog.tag.domain.TagCount(t.tag.name, COUNT(t)) " +
            "FROM " +
            "    PostTag t " +
            "WHERE " +
            "    t.isbn = ?1 " +
            "GROUP BY " +
            "    t.tagId " +
            "ORDER BY " +
            "    COUNT(t) DESC "
    )
    List<TagCount> getBookTagList(Long isbn);
}
