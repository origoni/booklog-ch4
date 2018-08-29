package com.bookonspring.booklog.tag.domain.repository;

import com.bookonspring.booklog.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByName(String tagName);
}
