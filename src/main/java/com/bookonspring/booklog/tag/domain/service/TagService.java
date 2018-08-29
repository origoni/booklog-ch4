package com.bookonspring.booklog.tag.domain.service;

import com.bookonspring.booklog.tag.domain.TagCount;
import com.bookonspring.booklog.tag.domain.entity.PostTag;
import com.bookonspring.booklog.tag.domain.entity.Tag;
import com.bookonspring.booklog.tag.domain.repository.PostTagRepository;
import com.bookonspring.booklog.tag.domain.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostTagRepository postTagRepository;


    @Transactional
    public void increaseUseCount(int tagIdx) {
        Tag tag = tagRepository.getOne(tagIdx);
        tag.setUpdatedAt(ZonedDateTime.now());
        tag.setUseCount(tag.getUseCount() + 1);
    }

    @Transactional
    public void decreaseUseCount(int tagIdx) {
        Tag tag = tagRepository.getOne(tagIdx);
        tag.setUpdatedAt(ZonedDateTime.now());
        tag.setUseCount(tag.getUseCount() - 1);
    }

    public Page<Tag> findAll(Pageable pageable) {

        Page<Tag> tags = tagRepository.findAll(pageable);
        return tags;
    }

    public List<TagCount> getBookTagList(long isbn) {
        List<PostTag> postTagList = postTagRepository.findByIsbn(isbn);


        Map<String, Long> tagList = postTagList
                .stream()
                .map(PostTag::getTag)
                .map(Tag::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<TagCount> tagCounts = tagList.entrySet()
                .stream()
                .map(e -> new TagCount(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(TagCount::getUseCount).reversed())
                .collect(Collectors.toList());

        return tagCounts;
    }

}
