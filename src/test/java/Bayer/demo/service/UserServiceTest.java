package Bayer.demo.service;

import Bayer.demo.dao.UserRepository;
import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.UserSaveDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void afterEach() {
        userRepository.deleteById(userRepository.findByLoginId("test").orElseThrow().getId());
    }

    @Test
    @DisplayName("회원 저장 테스트")
    void save() {
        //given
        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setLoginId("test");
        userSaveDto.setPassword("1234");
        userSaveDto.setNickname("test");
        userSaveDto.setEmail("test@test.com");

        //when
        Long saveId = userService.save(userSaveDto.toEntity());

        //then
        Optional<User> findUser = userRepository.findById(saveId);
        assertThat(userSaveDto.getLoginId()).isEqualTo(findUser.orElseThrow().getLoginId());
    }

    @Test
    @DisplayName("아이디 중복 체크")
    void dupLoginId() {
        //given
        UserSaveDto userSaveDto1 = new UserSaveDto();
        userSaveDto1.setLoginId("test");
        userSaveDto1.setPassword("1234");
        userSaveDto1.setNickname("test1");
        userSaveDto1.setEmail("test1@test.com");


        UserSaveDto userSaveDto2 = new UserSaveDto();
        userSaveDto2.setLoginId("test");
        userSaveDto2.setPassword("1234");
        userSaveDto2.setNickname("test2");
        userSaveDto2.setEmail("test2@test.com");

        //when
        userService.save(userSaveDto1.toEntity());
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.save(userSaveDto2.toEntity()));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

    @Test
    @DisplayName("닉네임 중복 체크")
    void dupNickname() {
        //given
        UserSaveDto userSaveDto1 = new UserSaveDto();
        userSaveDto1.setLoginId("test");
        userSaveDto1.setPassword("1234");
        userSaveDto1.setNickname("test");
        userSaveDto1.setEmail("test1@test.com");


        UserSaveDto userSaveDto2 = new UserSaveDto();
        userSaveDto2.setLoginId("test2");
        userSaveDto2.setPassword("1234");
        userSaveDto2.setNickname("test");
        userSaveDto2.setEmail("test2@test.com");

        //when
        userService.save(userSaveDto1.toEntity());
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.save(userSaveDto2.toEntity()));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");
    }

    @Test
    @DisplayName("이메일 중복 체크")
    void dupEmail() {
        //given
        UserSaveDto userSaveDto1 = new UserSaveDto();
        userSaveDto1.setLoginId("test");
        userSaveDto1.setPassword("1234");
        userSaveDto1.setNickname("test");
        userSaveDto1.setEmail("test@test.com");


        UserSaveDto userSaveDto2 = new UserSaveDto();
        userSaveDto2.setLoginId("test2");
        userSaveDto2.setPassword("1234");
        userSaveDto2.setNickname("test2");
        userSaveDto2.setEmail("test@test.com");

        //when
        userService.save(userSaveDto1.toEntity());
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.save(userSaveDto2.toEntity()));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");
    }

}