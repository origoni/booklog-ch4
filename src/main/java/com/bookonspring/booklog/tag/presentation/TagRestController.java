package com.bookonspring.booklog.tag.presentation;

import com.bookonspring.booklog.tag.domain.JsonViewFilter;
import com.bookonspring.booklog.tag.domain.TagCount;
import com.bookonspring.booklog.tag.domain.entity.Tag;
import com.bookonspring.booklog.tag.domain.repository.PostTagRepository;
import com.bookonspring.booklog.tag.domain.service.TagService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TagRestController {

    @Autowired
    private PostTagRepository postTagRepository;

    @Autowired
    private TagService tagService;


    @JsonView(JsonViewFilter.Public.class)
    @RequestMapping(value = "/api/v1/tags", method = RequestMethod.GET)
    public Page<Tag> tagsApi(@PageableDefault(sort = {"updatedAt"}, direction = Direction.DESC) Pageable pageable) {
        return tagService.findAll(pageable);
    }

    @JsonView(JsonViewFilter.Internal.class)
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Page<Tag> tags(@PageableDefault(sort = {"updatedAt"}, direction = Direction.DESC) Pageable pageable) {
        return tagService.findAll(pageable);
    }

    @RequestMapping(value = "/books/{isbn}/tags", method = RequestMethod.GET)
    public List<TagCount> tagCloudBook(@PathVariable long isbn) {
        return tagService.getBookTagList(isbn);
    }

    @RequestMapping(value = "/users/{userId}/tags", method = RequestMethod.GET)
    public List<TagCount> tagCloudUSer(@PathVariable String userId) {
        return postTagRepository.getUserTagList(userId);
    }

    @JsonView(JsonViewFilter.TagCount.class)
    @RequestMapping(value = "/tags/counts/jsonview", method = RequestMethod.GET)
    public List<Tag> tagList1(@PageableDefault(sort = {"updatedAt"}, direction = Direction.DESC) Pageable pageable) {
        return tagService.findAll(pageable).getContent();
    }

    @RequestMapping(value = "/tags/counts/dto", method = RequestMethod.GET)
    public List<TagDto> tagList2(@ModelAttribute @Valid TagCommand command) {
        Sort sort = Sort.by(new Sort.Order(Direction.DESC, "updatedAt")); //useCount
        Pageable pageable = PageRequest.of(command.getPage(), command.getSize(), sort);
        return tagService.findAll(pageable).getContent().stream().map(TagDto::new).collect(Collectors.toList());
    }

    @Data
    static class TagCommand {
        @Max(value = 100)
        int size = 20;
        int page = 0;
        long isbn = 0;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TagDto {
        String name;
        int useCount;

        TagDto(Tag tag) {
            this.name = tag.getName();
            this.useCount = tag.getUseCount();
        }
    }
}
