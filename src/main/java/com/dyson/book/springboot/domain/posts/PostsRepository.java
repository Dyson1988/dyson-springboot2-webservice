package com.dyson.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//                                                <@Entity Class, PK 타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {

    /*
        PostsRepository == (PostsDAO)
            - Posts 클래스로 Database를 접근하게 해줄 클래스다.

        extends JpaRepository<Posts, Long>
            - JpaRepository클래스를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
            - <@Entity클래스, PK타입>
            - <Posts, Long>
     */

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
