package com.dyson.book.springboot.web;

import com.dyson.book.springboot.config.auth.LoginUser;
import com.dyson.book.springboot.config.auth.dto.SessionUser;
import com.dyson.book.springboot.service.posts.PostsService;
import com.dyson.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    /*
        머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
        앞의 경로는 src/main/resources/templates로
        뒤의 파일 확장자는 .muistache가 붙는다.
        즉 여기선 return 값을 "index"로 반환하기 때문에
        src/main/resources/templates/index.mustache로 전환되어 view resolver가 처리하게 된다.

     */

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        //Model은 서버 템플릿 엔진에서 사용할수 있는 객체를 저장할 수 있다.
        //postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; //index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; //posts-save.mustache
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post", dto);

        return "posts-update";
    }




}
