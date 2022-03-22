package Bayer.demo.controller.user;

import Bayer.demo.dto.user.UserLoginDto;
import Bayer.demo.service.user.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @GetMapping("/login")
    public String userLogin(UserLoginDto userLoginDto){ return "/login"; }

    @PostMapping("/login")
    public String userLogin(UserLoginDto userLoginDto, BindingResult bindingResult, HttpServletRequest req) {
        String nickname = userLoginService.login(userLoginDto);
        if (nickname == null) {
            bindingResult.reject("failLogin");
            return "/login";
        }
        HttpSession session = req.getSession();
        session.setAttribute("loginUser", nickname);
        return "redirect:/";
    }
}
