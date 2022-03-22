package Bayer.demo.controller.board;

import Bayer.demo.service.board.ReplyDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/notice")
@RequiredArgsConstructor
public class ReplyDeleteController {

    private final ReplyDeleteService replyDeleteService;

    @PostMapping("/{noticeId}/{replyId}/delete")
    public String deleteReply(@PathVariable Long replyId, @PathVariable Long noticeId, HttpSession session){
        replyDeleteService.deleteReply(replyId);
        return "redirect:/board/notice/{noticeId}";
    }
}
