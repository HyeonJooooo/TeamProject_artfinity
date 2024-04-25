package com.example.project_1.Controller;

import com.example.project_1.service.UserService;
import com.example.project_1.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final UserService userService;
    //private final CommentService commentService;
    // 생성자를 통해 UserService를 주입받습니다.
    @Autowired
    public HomeController(UserService userService/*CommentService commentService*/) {
        this.userService = userService;
        //this.commentService = commentService;
    }

    // "/" 경로로 GET 요청이 들어왔을 때의 메소드입니다.
    @RequestMapping("/")
    public String home(@SessionAttribute("userId") Optional<Long> userIdOptional, HttpSession session, Model model) {
        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Long userId = userIdOptional.orElse(null);

        if (userId == null) {
            return "redirect:/login";
        } else {
            UserVo userVo = userService.getUserById(userId);
            //List<CommentVo> comments = commentService.getAllComments();

            model.addAttribute("user", userVo);
            //model.addAttribute("comments", comments);
            return "mypage";
        }
    }

}
