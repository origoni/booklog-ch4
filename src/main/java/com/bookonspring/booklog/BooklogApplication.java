package com.bookonspring.booklog;

import com.bookonspring.booklog.book.domain.*;
import com.bookonspring.booklog.post.domain.PostCommand;
import com.bookonspring.booklog.post.domain.entity.Post;
import com.bookonspring.booklog.post.domain.repository.PostRepository;
import com.bookonspring.booklog.tag.domain.TagCount;
import com.bookonspring.booklog.tag.domain.repository.PostTagRepository;
import com.bookonspring.booklog.tag.domain.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class BooklogApplication {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ReadBookRepository readBookRepository;

    @Autowired
    PostTagRepository postTagRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostTagService postTagService;


    public static void main(String[] args) {
        SpringApplication.run(BooklogApplication.class, args);
    }

    @PostConstruct
    public void init() {

        bookRepository.save(new Book(9788997924035L, "자바의 신 VOL.1, 기초 문법편", "이상민", "로드북", "/book_cover/9788997924035.jpeg", LocalDate.of(2013, 2, 28)));
        bookRepository.save(new Book(9788997924042L, "자바의 신 Vol.2, 주요 API 응용편", "이상민", "로드북", "/book_cover/9788997924042.jpeg", LocalDate.of(2013, 4, 26)));
        Book book = bookRepository.save(new Book(9788997924325L, "자바의 신, 2nd Edition (전2권)", "이상민", "로드북", "/book_cover/9788997924325.png", LocalDate.of(2017, 6, 23)));
        bookRepository.save(new Book(9788997924271L, "파이썬의 신: 프로그래밍 언어를 배운다는 것", "김주현", "로드북", "/book_cover/9788997924271.png", LocalDate.of(2016, 12, 23)));
        bookRepository.save(new Book(9788997924202L, "안드로이드의 신: 원리와 예제로 배운다", "남진하", "로드북", "/book_cover/9788997924202.jpeg", LocalDate.of(2016, 5, 10)));


        bookRepository.save(new Book(9788997924103L, "백견불여일타 JSP&Servlet", "성윤정", "로드북", "/book_cover/9788997924103.jpeg", LocalDate.of(2014, 7, 28)));
        bookRepository.save(new Book(9788997924240L, "자바 웹 프로그래밍 Next Step", "박재성", "로드북", "/book_cover/9788997924240.png", LocalDate.of(2016, 9, 19)));


        postTagService.insertPostTag(new PostCommand(postRepository.save(new Post(9788997924325L, "자바의 기본을 확실하게!!", "자바 기본서로서 꼭 필요한 내용을 다룬다.", "origoni")), "자바 기본서 자바8 친절한설명 목욕의신"));
        postTagService.insertPostTag(new PostCommand(postRepository.save(new Post(9788997924325L, "여러 기본서 들이 있지만..", "웹 개발쪽으로 추가적인 정보도 있어서 좋음..", "origoni")), "자바 웹개발 웹 기본서 실무"));
        postRepository.save(new Post(9788997924325L, "기초서적으로 자바 8에 대해서도 알고 싶다면..", "개정되면서 자바 8에서 변경된 부분을 담고 있다.", "origoni"));

        postTagService.insertPostTag(new PostCommand(postRepository.save(new Post(9788997924103L, "JSP에 대해 알게되었다.", "회사에서 JSP를 사용하는데.. 궁금한점을 많이 알려준다.", "origoni")), "자바 기본서 JSP 자바서버페이지 웹 예제많음 따라하기 실무"));
        postTagService.insertPostTag(new PostCommand(postRepository.save(new Post(9788997924240L, "더 깊은 곳으로...", "단지, 눈에 보이던 것만 개발하던 당신에게 새로운 것을 보여 줄 것이다.", "origoni")), "자바 웹 웹개발 중급서 어려움"));


        bookRepository.save(new Book(9788997924370L, "핵심 문법과 예제로 배우는 코틀린", "이난주", "로드북", "/book_cover/9788997924370.png", LocalDate.of(2017, 9, 6)));
        bookRepository.save(new Book(9788997924295L, "실용주의 소프트웨어 개발: 현장에서 길어올린 소프트웨어 개발 베스트 프랙티스(Best Practices)", "오병곤", "로드북", "/book_cover/9788997924295.png", LocalDate.of(2017, 4, 10)));
        bookRepository.save(new Book(9788997924257L, "헬로, 스타트업: 제품, 기술, 팀을 완벽하게 구축하는 기술", "예브게니 브리크만", "로드북", "/book_cover/9788997924257.jpeg", LocalDate.of(2016, 9, 19)));
        bookRepository.save(new Book(9788997924097L, "프로그래머 철학을 만나다: 소프트웨어를 사랑하는 기술", "유석문", "로드북", "/book_cover/9788997924097.jpeg", LocalDate.of(2014, 1, 22)));

        postRepository.save(new Post(9788997924097L, "철학과 프로그래밍의 접점을 찾아서", "개발을 하다보면 여러가지 질문에 답변을 해야 하고, 때론 논리적이지 못한 부분에 대한 대답을 찾아야 할 때도 있다.", "origoni"));
        postRepository.save(new Post(9788997924370L, "코틀린 기본 서적", "JVM에서 돌아가는 여러가지 언어들이 있지만, 요즘 안드로이드에서도 사용되고 핫한 언어 코틀린을 알아보자.", "origoni"));

        log.warn("bookRepository.findAll()={}", bookRepository.findAll());

        log.warn("postRepository.findAll()={}", postRepository.findAll());


        List<TagCount> tagCounts = postTagRepository.getBookTagList(9788997924325L);
        log.warn("tagCounts={}", tagCounts);


        Set<Category> categories = new HashSet<>();

        Category category = new Category();
        category.setName("Java");
        categories.add(category);

        category = new Category();
        category.setName("DEV");
        categories.add(category);


        ReadBook readBook = new ReadBook();
//        readBook.setId(new ReadBookId("origoni", book));
        readBook.setBook(book);
        readBook.setUserId("origoni");
        readBook.setReadType(ReadType.DONE);
        readBook.setCategories(categories);
        readBookRepository.save(readBook);

        log.warn("readBookRepository.findAll()={}", readBookRepository.findAll());

//        log.warn("readBookRepository.findById(new ReadBookId(\"origoni\", 9788997924325L))={}", readBookRepository.findById(new ReadBookId("origoni", book)));
        log.warn("readBookRepository.findById(new ReadBookId(\"origoni\", 9788997924325L))={}", readBookRepository.findById(new ReadBookId("origoni", 9788997924325L)));


        List<Category> categories2 = categoryRepository.findAll();

        log.warn("categoryRepository.findAll()={}", categories2);
        log.warn("categoryRepository.findAll()={}", categoryRepository.findAll());

        List<Category> categoryList = categoryRepository.findAllById(categories.stream().map(Category::getName).collect(Collectors.toList()));

        for (Category c : categoryList) {
            c.setUseCnt(c.getUseCnt() + 1);
        }
        log.warn("categoryList={}", categoryList);

        categoryRepository.saveAll(categoryList);

        log.warn("categoryRepository.findAll()={}", categoryRepository.findAll());

    }

}
