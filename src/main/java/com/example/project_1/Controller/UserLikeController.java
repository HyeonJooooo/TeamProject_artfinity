package com.example.project_1.Controller;

import com.example.project_1.service.UserLikeService;
import com.example.project_1.vo.UserLikeVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserLikeController {

    private final UserLikeService userLikeService;

    public UserLikeController(UserLikeService userLikeService) {
        this.userLikeService = userLikeService;
    }

    @PostMapping("/userlike/add")
    public String addUserLike(UserLikeVo userlikeVo, @SessionAttribute("userId") Optional<Long> userIdOptional, HttpSession session, Model model) {

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Long userId = userIdOptional.orElseThrow(() -> new IllegalArgumentException("User ID must not be null"));
        userlikeVo.setUserId(userId); //userId 설정

        // 좋아요 디비에 입력
        userLikeService.addUserLike(userlikeVo);

        // 좋아요 정보를 모델에 추가
        userlikeVo = userLikeService.getUserLikeById(userlikeVo.getUserId());
        model.addAttribute("userlikeVo", userlikeVo);

        return "redirect:/project_detail";
    }

    @PostMapping("/userlike/remove")
    public String removeUserLike(@SessionAttribute("userId") Optional<Long> userIdOptional, Model model) {
        Long userId = userIdOptional.orElseThrow(() -> new IllegalArgumentException("User ID must not be null"));

        userLikeService.removeUserLikeByUserId(userId);

        return "redirect:/project_detail";
    }

}
