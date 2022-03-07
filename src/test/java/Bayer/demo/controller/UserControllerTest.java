package Bayer.demo.controller;

import Bayer.demo.dao.UserRepository;
import Bayer.demo.dto.user.UserLoginDto;
import Bayer.demo.dto.user.UserSaveDto;
import Bayer.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("회원가입 페이지 리턴")
    void signUpPageReturn_Test() throws Exception {
        mvc.perform(get("/user/join"))
                .andDo(print())
                .andExpect(model().attributeExists("userSaveDto"))
                .andExpect(status().isOk())
                .andExpect(view().name("/signup"));
    }

    @Test
    @DisplayName("회원가입 완료 테스트")
    void insertUser_Test() throws Exception {
        //given
        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setLoginId("test");
        userSaveDto.setPassword("1234");
        userSaveDto.setNickname("test");
        userSaveDto.setEmail("test@test.com");
        userService.save(userSaveDto.toEntity());

        mvc.perform(post("/join")
                        .param("userSaveDto", String.valueOf(userSaveDto)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("로그인 페이지 리턴")
    void loginPageReturn_Test() throws Exception {
        mvc.perform(get("/user/login"))
                .andDo(print())
                .andExpect(model().attributeExists("userLoginDto"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setLoginId("Gen");
        userLoginDto.setPassword("1234");
    }

}
