package com.bookonspring.booklog.tag.domain.service;

import com.bookonspring.booklog.post.domain.PostCommand;
import com.bookonspring.booklog.tag.domain.entity.PostTag;
import com.bookonspring.booklog.tag.domain.entity.Tag;
import com.bookonspring.booklog.tag.domain.repository.PostTagRepository;
import com.bookonspring.booklog.tag.domain.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

@Slf4j
@Service
public class PostTagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostTagRepository postTagRepository;

    @Autowired
    TagService tagService;


    public void insertPostTag(PostCommand postCommand) {

        HashSet<String> hashSet = tagNamesToHashSet(postCommand.getTags());

        insertPostTag(postCommand.getBook().getIsbn(), postCommand.getId(), hashSet);
    }

    public void updatePostTag(PostCommand postCommand) {

        List<PostTag> oldPostTagList = postTagRepository.findByPostId(postCommand.getId());
        HashSet<String> newTagHashSet = tagNamesToHashSet(postCommand.getTags());

        Iterator<String> newTagIterator = newTagHashSet.iterator();
        while (newTagIterator.hasNext()) {
            String newTag = newTagIterator.next();
            Iterator<PostTag> oldTagIterator = oldPostTagList.iterator();
            while (oldTagIterator.hasNext()) {
                String oldTag = oldTagIterator.next().getTag().getName();
                if (newTag.equals(oldTag)) {
                    newTagIterator.remove();
                    oldTagIterator.remove();
                }
            }
        }

        if (newTagHashSet != null) {
            insertPostTag(postCommand.getBook().getIsbn(), postCommand.getId(), newTagHashSet);
        }
        if (oldPostTagList != null) {
            deleteTag(oldPostTagList);
        }

    }

    public void deletePostTagByPostId(int postId) {
        List<PostTag> postTagList = postTagRepository.findByPostId(postId);

        deleteTag(postTagList);
    }

    private HashSet<String> tagNamesToHashSet(String postTagNames) {

        StringTokenizer tokenTag = new StringTokenizer(postTagNames.trim().replaceAll("[ ]+", " "), " ");

        HashSet<String> hashSet = new HashSet<>();

        while (tokenTag.hasMoreElements()) {
            hashSet.add(tokenTag.nextToken());
            if (hashSet.size() >= 10) {
                break;
            }
        }

        return hashSet;
    }

    private void insertPostTag(long isbn, int postId, HashSet<String> hashSet) {
        for (String tagName : hashSet) {
            if (tagName.equals("")) {
                continue;
            }

            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                tag = tagRepository.save(new Tag(tagName));
            }

            insertPostTag(new PostTag(isbn, postId, tag.getId()));
        }
    }

    private void insertPostTag(PostTag postTag) {
        postTag.setRegDate(new Date());
        postTagRepository.save(postTag);

        tagService.increaseUseCount(postTag.getTagId());
    }

    private void deleteTag(List<PostTag> postTagList) {
        for (PostTag postTag : postTagList) {
            postTagRepository.deleteById(postTag.getId());

            tagService.decreaseUseCount(postTag.getTagId());
        }
    }

}