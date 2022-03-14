package Bayer.demo.controller.main;

import Bayer.demo.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("userLoginDto", new User());
        return "/home";
    }

}
