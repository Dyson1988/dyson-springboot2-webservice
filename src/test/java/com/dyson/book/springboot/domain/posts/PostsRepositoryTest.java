package com.dyson.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정 없이 사용하면 H2 데이터베이스를 자동으로 실행해 준다.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given (준비)
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //id값이 있으면 update / 없으면 insert
        postsRepository.save(Posts.builder()            //id값이 없기 때문에 Insert가 된다.
                                            .title(title)
                                            .content(content)
                                            .author("docyoungs@gmail.com")
                                            .build());

        //when (실행)
        List<Posts> postsList = postsRepository.findAll(); //findAll() 모든 데이터를 조회한다.

        //then (검증)
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title); //title과 일치한다.
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                                            .title("title")
                                            .content("content")
                                            .author("author")
                                            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> createDate=" + posts.getCreatedDate()
                        +", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
