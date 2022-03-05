package Bayer.demo.controller;

import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.UserLoginDto;
import Bayer.demo.dto.user.UserSaveDto;
import Bayer.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String getJoin(UserSaveDto userSaveDto) {
        return "/signup";
    }

    @PostMapping("/join")
    public String postJoin(UserSaveDto userSaveDto, BindingResult result) {
        if (result.hasErrors()) return "/signup";
        try{
            userService.save(userSaveDto.toEntity());
        } catch (IllegalStateException e) {
            result.reject("signUpFail",e.getMessage());
            return "/signup";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLogin(UserLoginDto userLoginDto){ return "/login"; }

    @PostMapping("/login")
    public String postLogin(UserLoginDto userLoginDto, BindingResult bindingResult) {
        User findId = userService.login(userLoginDto.getLoginId(), userLoginDto.getPassword());
        if (findId == null) {
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 일치하지 않습니다.");
            return "/login";
        }
        return "redirect:/";
    }
}
