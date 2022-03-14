package Bayer.demo.controller.board;

import Bayer.demo.domain.user.User;
import Bayer.demo.dto.board.BoardSaveDto;
import Bayer.demo.service.board.BoardPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardPostingController {

    private final BoardPostingService boardPostingService;

    @GetMapping("/notice/write")
    public String noticePosting(BoardSaveDto boardSaveDto) {
        return "/board/notice_posting";
    }

    @PostMapping("/notice/write")
    public String noticePosting(BoardSaveDto boardSaveDto, HttpSession session) {
        boardPostingService.save(boardSaveDto, (User) session.getAttribute("loginMember"));
        return "redirect:/board/notice";
    }
}
