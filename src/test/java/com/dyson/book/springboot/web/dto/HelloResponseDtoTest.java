package com.dyson.book.springboot.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given (준비)
        String name = "Hello Haena's";
        int amount = 1000;

        //when (실행)
        //HelloResponseDto에 선언된 @RequiredArgsConstructor가
        //선언된 모든 final 필드가 포함된 생성자를 생성해 준다. (꼭 final이 붙었을때)
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then (검증)
        assertThat(dto.getName()).isEqualTo(name);      //name과 일치 합니다.
        assertThat(dto.getAmount()).isEqualTo(amount);  //amount와 일치 한다.


        /*
            assertThat
                - assertj라는 테스트 검증 라이브러리의 검증 메소드이다.
                - 검증하고 싶은 대상을 메소드 인자로 받는다.
                - 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.

            isEqualTo
                - assertj의 동등 비교 메소드 이다.
                - assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공이다.
         */
    }




}
