package Bayer.demo.controller.board;

import Bayer.demo.service.board.BoardMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardMainController {

    private final BoardMainService boardService;

    @GetMapping("/notice")
    public String noticeMain(Model model) {
        model.addAttribute("noticeList",boardService.findAll());
        return "/board/notice";
    }

}
