package Bayer.demo.controller.main;

import Bayer.demo.service.board.BoardMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardMainService boardMainService;

    @GetMapping("/")
    public String homeView(Model model) {
        model.addAttribute("noticeList",boardMainService.findAll());
        return "/home";
    }
}
