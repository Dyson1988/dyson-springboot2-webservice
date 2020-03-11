#!/usr/bin/env bash

# 쉬고 있는 profile 찾기 : real1이 사용 중이면 real2가 대기, real2가 사용 중이면 real1이 대기
function find_idle_profile() {

    # 현재 엔진엑스가 바라보고 있는 active 스프링부트가 정상적으로 수행 중인지 확인한다.
    # 응답값을 HttpStatus로 받으며 정상이면200, 비정상이면 400~503을 받으며 이때는 real2를 active profile로 사용한다.
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면 (즉, 40x or 50x 에러 모두 포함)
    then
        CURRENT_PROFILE=real2
    else
        CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
        IDLE_PROFILE=real2
    else
        IDLE_PROFILE=real1
    fi

    # bash 스크립트는 값을 return(반환)해주는 기능이 없다.
    # 그래서 echo로 결과를 출력 후, 클라이언트가 값을 잡아 ($(find_idle_profile)) 사용한다.
    # 중간에 echo를 사용하면 안된다.
    echo "${IDLE_PROFILE}"

}

# 대기 중인 profile의 port 찾기
function find_idle_port() {
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
        echo "8081"
    else
        echo "8082"
    fi

}