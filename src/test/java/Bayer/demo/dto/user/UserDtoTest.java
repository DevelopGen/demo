package Bayer.demo.dto.user;

import Bayer.demo.domain.user.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    @Test
    @DisplayName("롬복 테스트")
    void Dto() {
        //given
        String loginId = "test";
        String nickname = "테스터";
        String password = "test1234";
        String email = "test@test.com";

        //when
        UserSaveDto userSaveDto = UserSaveDto.builder()
                .loginId(loginId).nickname(nickname)
                .password(password).email(email)
                .build();

        //then
        assertThat(userSaveDto.getLoginId()).isEqualTo(loginId);
        assertThat(userSaveDto.getNickname()).isEqualTo(nickname);
        assertThat(userSaveDto.getPassword()).isEqualTo(password);
    }
}