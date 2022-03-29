package Bayer.demo.controller.user;

import Bayer.demo.config.ImgSet;
import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.UserEditDto;
import Bayer.demo.dto.user.UserPasswordEditDto;
import Bayer.demo.repository.UserRepository;
import Bayer.demo.service.user.UserEditService;
import Bayer.demo.service.user.UserProfileImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserEditController {

    private final UserEditService userEditService;
    private final UserRepository userRepository;
    private final UserProfileImgService userProfileImgService;
    private final ImgSet imgSet;


    @GetMapping("/profile")
    public String overView(Model model, HttpSession session) {
        if (session.getAttribute("loginUser") == null) return "redirect:/user/login";
        User loginUser = userRepository.findByNickname(session.getAttribute("loginUser").toString()).orElse(null);
        model.addAttribute("userEditDto", loginUser);
        return "/user/user_profile";
    }

    @GetMapping("/editInfo")
    public String editInfo(Model model, HttpSession session) {
        if (session.getAttribute("loginUser") == null) return "redirect:/user/login";
        User loginUser = userRepository.findByNickname(session.getAttribute("loginUser").toString()).orElse(null);
        model.addAttribute("userEditDto", loginUser);
        return "/user/user_edit";
    }

    @PostMapping("/editInfo")
    public String editProfile(@Valid UserEditDto userEditDto, @RequestParam(value = "imgFile", required = false) MultipartFile file, HttpSession session, BindingResult result) {
        String nickname;
        User loginUser = userRepository.findByNickname(session.getAttribute("loginUser").toString()).orElse(null);
        if (!file.isEmpty()) userProfileImgService.saveImg(imgSet.ProfileImgUpload(file, loginUser));
        try{
            nickname = userEditService.editUserInfo(session.getAttribute("loginUser").toString(), userEditDto);
        } catch (IllegalStateException e){
            result.reject("editFail",e.getMessage());
            return "/user/user_edit";
        }
        session.setAttribute("loginUser", nickname);
        return "redirect:/user/profile";
    }

    @GetMapping("/editPassword")
    public String editPassword(UserPasswordEditDto userPasswordEditDto, Model model, HttpSession session) {
        if (session.getAttribute("loginUser") == null) return "redirect:/user/login";
        User loginUser = userRepository.findByNickname(session.getAttribute("loginUser").toString()).orElse(null);
        model.addAttribute("userEditDto", loginUser);
        return "/user/user_password";
    }

    @PostMapping("/editPassword")
    public String editPassword(@Valid UserPasswordEditDto userPasswordEditDto, HttpSession session, BindingResult result, Model model) {

        try{
            userEditService.editPassword(session.getAttribute("loginUser").toString(), userPasswordEditDto);
        } catch (IllegalStateException e){
            result.reject("editFail",e.getMessage());
            return "/user/user_password";
        }
        session.invalidate();
        model.addAttribute("status","pass");
        return "signup_after";
    }
}
