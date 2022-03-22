package Bayer.demo.controller.user;

import Bayer.demo.dto.user.UserFindDto;
import Bayer.demo.dto.user.UserPasswordEditDto;
import Bayer.demo.service.user.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserFindController {

    private final UserFindService userFindService;

    @GetMapping("/findid")
    public String findUserId(UserFindDto userFindDto) {
        return "user/user_find_id";
    }

    @GetMapping("/findpw")
    public String findUserPw(UserFindDto userFindDto) {
        return "user/user_find_pw";
    }

    @PostMapping("/findid")
    public String findUserId(UserFindDto userFindDto, BindingResult result, RedirectAttributes red) {
        String findId;
        try {
            findId = userFindService.findUserId(userFindDto.getEmail());
        } catch (IllegalStateException e){
            result.reject("failFind",e.getMessage());
            return "user/user_find_id";
        }
        red.addAttribute("loginId", findId);
        return "redirect:/user/findid";
    }

    @PostMapping("/findpw") // <- 미완성
    public String findUserPw(UserFindDto userFindDto, UserPasswordEditDto userPasswordEditDto, BindingResult result) {
        try {
            userFindService.findUserPw(userFindDto);
        } catch (IllegalStateException e) {
            result.reject("failFind",e.getMessage());
            return "user/user_find_pw";
        }
        return "user/user_find_edit_pw";
    }
}
