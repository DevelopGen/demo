package Bayer.demo.controller.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.service.board.BoardViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardViewController {

    private final BoardViewService boardViewService;

    @GetMapping("/notice/{id}")
    public String noticeView(@PathVariable Long id, Model model){
        Board findNotice = boardViewService.findBoard(id);
        model.addAttribute("notice",findNotice);
        return "/board/notice_view";
    }
}
