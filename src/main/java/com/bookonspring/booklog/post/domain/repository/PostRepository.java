package com.bookonspring.booklog.post.domain.repository;

import com.bookonspring.booklog.post.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByBookIsbn(long isbn);

    Page<Post> findByUserId(Pageable pageable, String userId);
}
