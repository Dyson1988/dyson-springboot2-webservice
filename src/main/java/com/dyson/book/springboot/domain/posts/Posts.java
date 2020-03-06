package com.dyson.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //기본생성자 자동 추가
@Entity             //클래스 내 모든 필드의 Getter 메소드를 자동생성 (Entity클래스에는 절때 Setter를 만들지 않는다)
public class Posts extends BaseTimeEntity {// 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity class라고 한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /*
        @Entity
            - 테이블과 링크될 클래스임을 나타낸다.
            - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭 한다.
            - ex) SalesManager.java -> sales_manager_table

        @Id
            - 해당 테이블의 PK 필드를 나타낸다.

        @GeneratedValue
            - PK의 생성 규칙을 나타낸다.
            - 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auth_increment가 된다.

        @Column
            - 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다.
            - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
            - 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex:title),
                타입을 TEXT로 변경하고 싶거나(ex:content)등의 경우에 사용된다.

        @Builder
            - 해당 클래스의 빌더 패턴 클래스를 생성
            - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함

        서비스 초기 구축 단게에선 테이블 설계(여기선 Entity 설계)가 빈번하게 변경되는데, 이때 롬복의 어노테이션들은
        코드 변경량을 최소화시켜 주기 때문에 적극적으로 사용한다.
     */

}
