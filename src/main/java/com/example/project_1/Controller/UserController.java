package com.example.project_1.Controller;

import com.example.project_1.service.UserService;
import com.example.project_1.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    //생성자 주입 post에서 userService사용할 때 필요
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    // 로그인 페이지로 이동하는 메서드 - GET 요청
    @GetMapping("/login")
    public String toLoginPage(HttpSession session) { // 로그인 페이지
        Long id = (Long) session.getAttribute("userId");
        if (id != null) { // 로그인된 상태
            return "redirect:/";
        }
        return "login"; // 로그인되지 않은 상태
    }

    // 로그인 처리를 하는 메서드 - POST 요청
    @PostMapping("/login")
    public String login(String email, String password, HttpSession session, RedirectAttributes redirectAttributes) {
        UserVo userVo = userService.login(email, password);
        if (userVo  == null) { // 로그인 실패
            return "login";
        }
        session.setAttribute("userId", userVo.getUserId()); // 로그인 성공, 세션에 ID 저장
        session.setAttribute("userName", userVo.getUserName());
        redirectAttributes.addFlashAttribute("userId", userVo); // RedirectAttributes에 userId 추가
        return "redirect:/"; // 메인 페이지로 리다이렉트
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserVo userVo, RedirectAttributes redirectAttributes) {
        if (userVo.getUserName() == null || userVo.getUserName().trim().isEmpty()) {
            // userName이 비어있는 경우, 에러 메시지를 설정하고 회원가입 양식으로 리다이렉트
            redirectAttributes.addFlashAttribute("signupError", "이름을 입력해주세요.");
            return "redirect:/signup";
        }

        // 현재 시간 설정
        java.sql.Timestamp now = new java.sql.Timestamp(new java.util.Date().getTime());
        userVo.setJoinDate(now);

        // 나머지 필드들도 동일하게 검증
        userService.signup(userVo);
        return "redirect:/login";
    }
}
