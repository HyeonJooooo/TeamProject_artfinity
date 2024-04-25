package com.example.project_1.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project_1.service.CommentService;
import com.example.project_1.service.UserService;
import com.example.project_1.vo.UserVo;
import com.example.project_1.vo.CommentVo;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectDetailController {

    private final UserService userService;
    private final CommentService commentService;
    // 생성자를 통해 UserService를 주입받습니다.
    @Autowired
    public ProjectDetailController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/project_detail")
    public String projectDetail(@SessionAttribute("userId") Optional<Long> userIdOptional, HttpSession session, Model model) {

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Long userId = userIdOptional.orElse(null);

        if (userId == null) {
            return "redirect:/login";
        } else {
            UserVo userVo = userService.getUserById(userId);
            List<CommentVo> comments = commentService.getAllComments();
            int commentCount = commentService.countComments();

            model.addAttribute("user", userVo);
            model.addAttribute("comments", comments);
            model.addAttribute("commentCount", commentCount);

            return "project_detail";
        }
    }
}