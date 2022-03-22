package Bayer.demo.controller.board;

import Bayer.demo.dto.board.ReplySaveDto;
import Bayer.demo.service.board.ReplyPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyPostingController {

    private final ReplyPostingService replyPostingService;

    @PostMapping("/board/notice/{id}/reply")
    public String noticePosting(@PathVariable Long id, ReplySaveDto replySaveDto, HttpSession session) {
        replyPostingService.save(replySaveDto, id, session.getAttribute("loginUser").toString());
        return "redirect:/board/notice/{id}";
    }
}
