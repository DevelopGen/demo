package Bayer.demo.controller.user;

import Bayer.demo.dto.user.UserSaveDto;
import Bayer.demo.service.user.UserSignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserSignupController {

    private final UserSignupService userSignupService;

    @GetMapping("/join")
    public String getJoin(UserSaveDto userSaveDto) {
        return "/signup";
    }

    @PostMapping("/join")
    public String postJoin(@Valid UserSaveDto userSaveDto, BindingResult result, Model model) {
        if (result.hasErrors()) return "/signup";
        try{
            userSignupService.save(userSaveDto.toEntity());
        } catch (IllegalStateException e) {
            result.reject("signUpFail",e.getMessage());
            return "/signup";
        }
        model.addAttribute("status","good");
        return "signup_after";
    }
}
