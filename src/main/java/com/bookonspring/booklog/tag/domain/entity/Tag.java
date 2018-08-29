package com.bookonspring.booklog.tag.domain.entity;

import com.bookonspring.booklog.tag.domain.JsonViewFilter;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewFilter.Internal.class)
    private int id;

    @JsonView({JsonViewFilter.Public.class, JsonViewFilter.TagCount.class})
    @Column(unique = true)
    private String name;

    @JsonView({JsonViewFilter.Public.class, JsonViewFilter.TagCount.class})
    private int useCount;

    @UpdateTimestamp
    @JsonView(JsonViewFilter.Public.class)
    private ZonedDateTime updatedAt;

    @CreationTimestamp
    @JsonView(JsonViewFilter.Public.class)
    private ZonedDateTime createdAt;

    public Tag(String tagName) {
        this.name = tagName;
    }

}
