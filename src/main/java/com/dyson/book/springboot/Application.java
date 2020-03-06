package com.dyson.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing 삭제
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /*
            main 메소드에서 실행하는 SpringApplication.run으로 인해 내장 WAS를 실행한다.
            이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진
            Jar파일(실행 가능한 Java 패키징 파일)로 실행하면 된다.

            # 꼭 스프링 부트에서만 내장 WAS를 사용할 수 있는 것은 아니지만
            스프링 부트에서는 내장 WAS를 사용하는 것을 권장하고 있다.
            '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문이다.
         */
        SpringApplication.run(Application.class, args); //내장 WAS를 실행
    }
}

