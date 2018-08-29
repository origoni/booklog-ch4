# booklog-ch4

아직 확정된 버전이 아닙니다. (수정할 부분도 많습니다.)
본문에 들어갈 부분을 추려서 다시 작업이 필요합니다.

project booklog-ch4

- Spring Boot 2.1
- Bootstrap 4
  - https://getbootstrap.com/docs/4.1/examples/blog/

## Quick Start
Pre Installed
- JDK 1.8 (or Java 10)
- Maven 3.5
- Git

Run
```
git clone https://github.com/origoni/booklog-ch4.git
cd booklog-ch4
mvn spring-boot:run
```

## Test

http://localhost:8040


### Tested
- STS(Eclipse) 4.0.0.M14
- IntelliJ IDEA 2018.2.1

```
//@formatter:off & //@formatter:on
eclipse : Preferences -> Java -> Code style -> Formatter -> Edit... (or New...) > Off/On Tags
intellij : Preferences -> Editor -> Code Style > Formatter Control > Enable formatter markers in comments
```


## Dependency

### Spring Boot 2.1
- spring-boot-starter-web
- spring-boot-starter-thymeleaf
- spring-boot-starter-data-jpa

#### Environment
- Java version: 8 or 10
- Spring Boot version: 2.1.0.M2
- Maven version: 3.5.4
- Lombok version: 1.18.2
- Default Encoding: UTF-8
- Default SCM : git
