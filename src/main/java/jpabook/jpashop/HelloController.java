package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // Model에 값을 담아 View로 전달 가능하다.
        model.addAttribute("data", "C5ng");
        return "hello"; // 화면명 -> resource/templates/hello.html
    }
}
