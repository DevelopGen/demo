package Bayer.demo.dao;

import Bayer.demo.domain.user.Role;
import Bayer.demo.domain.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteById(userRepository.findByLoginId("test").orElseThrow().getId());
    }

    @Test
    @DisplayName("회원가입 테스트")
    void save() {
        //given
        String loginId = "test";
        String password = "1234";
        String nickname = "test";

        userRepository.save(User.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email("test@test.com")
                .role(Role.USER)
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(1);
        assertThat(user.getLoginId()).isEqualTo(loginId);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @Test
    @DisplayName("이메일로 찾기")
    void findByEmail() {
        //given
        String email = "test@test.com";

        userRepository.save(User.builder()
                .loginId("test")
                .password("1234")
                .nickname("test")
                .email(email)
                .role(Role.USER)
                .build());

        //when
        Optional<User> findId = userRepository.findByEmail(email);

        //then
        assertThat(findId.orElseThrow().getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("아이디로 찾기")
    void findByLoginId() {
        //given
        String loginId = "test";

        userRepository.save(User.builder()
                .loginId(loginId)
                .password("1234")
                .nickname("test")
                .email("test@test.com")
                .role(Role.USER)
                .build());

        //when
        Optional<User> findId = userRepository.findByLoginId(loginId);

        //then
        assertThat(findId.orElseThrow().getLoginId()).isEqualTo(loginId);
    }

    @Test
    @DisplayName("닉네임으로 찾기")
    void findByNickname() {
        //given
        String nickname = "test";

        userRepository.save(User.builder()
                .loginId("test")
                .password("1234")
                .nickname(nickname)
                .email("test@test.com")
                .role(Role.USER)
                .build());

        //when
        Optional<User> findId = userRepository.findByNickname(nickname);

        //then
        assertThat(findId.orElseThrow().getNickname()).isEqualTo(nickname);
    }

}