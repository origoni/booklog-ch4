package com.bookonspring.booklog.book.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Category {

    public Category(String name, int useCnt) {
        this.name = name;
        this.useCnt = useCnt;
    }

    @Id
    String name;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<ReadBook> readBookList;

    int useCnt;

    public void increaseUseCount() {
        this.useCnt = this.useCnt + 1;
    }

    public void decreaseUseCount() {
        this.useCnt = this.useCnt - 1;
    }
}
