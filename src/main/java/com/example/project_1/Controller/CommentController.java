package com.example.project_1.Controller;

import com.example.project_1.service.CommentService;
import com.example.project_1.service.UserService;
import com.example.project_1.vo.UserVo;
import com.example.project_1.vo.CommentVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/comment")
    public String addComment(CommentVo commentVo, UserVo userVo, @SessionAttribute("userId") Optional<Long> userIdOptional, HttpSession session, Model model) {

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Long userId = userIdOptional.orElseThrow(() -> new IllegalArgumentException("User ID must not be null"));
        commentVo.setUserId(userId.intValue()); // CommentVo의 userId 설정

        // 현재 시간 설정
        java.sql.Timestamp now = new java.sql.Timestamp(new java.util.Date().getTime());
        commentVo.setCreated_at(now);
        commentVo.setUpdated_at(now);

        // 사용자 이름 설정 (세션 또는 DB 조회)
        // 예: session.getAttribute("userName") 또는 사용자 서비스 사용
        commentVo.setUserName(session.getAttribute("userName").toString());

        // 댓글 디비에 입력
        commentService.addComment(commentVo);

        // 모든 댓글 목록을 다시 가져와 모델에 추가
        List<CommentVo> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);

        // 사용자정보를 모델에 추가
        userVo = userService.getUserById(userId);
        model.addAttribute("user", userVo);

        // 댓글 정보를 모델에 추가
        commentVo = commentService.getCommentById(commentVo.getCommentId());
        model.addAttribute("commentVo", commentVo);

        int commentCount = commentService.countComments();
        model.addAttribute("commentCount", commentCount);

        return "project_detail"; // 사용자를 댓글이 추가된 후 보여줄 페이지로 리다이렉션
    }

    /*@GetMapping("/comment") // 또는 적절한 엔드포인트 URL 사용
    public String showCommentCount(Model model) {
        int commentCount = commentService.countComments();
        model.addAttribute("commentCount", commentCount);

        // 뷰 이름을 반환합니다. 예를 들어, 'detail_page'는 실제 보여주려는 페이지의 뷰 이름입니다.
        return "detail_page";
    }*/

    @PostMapping("/delete-comment")
    public String deleteComment(@SessionAttribute("userId") Optional<Long> userIdOptional, @RequestParam("commentId") Long commentId, RedirectAttributes redirectAttributes, Model model) {
        commentService.deleteComment(commentId);
        return "redirect:/project_detail"; // 또는 적절한 뷰로 리다이렉션
    }

    @PostMapping("/update-comment")
    public ResponseEntity<String> updateComment(@RequestParam("commentId") long commentId, @RequestParam("content") String content) {
        commentService.updateComment(commentId, content);
        return null;
    }


// ... 나머지 코드 ...


}
