package Bayer.demo.dto.user;

import Bayer.demo.domain.user.Role;
import Bayer.demo.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
public class UserSaveDto {
    @NotBlank
    private String loginId;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
    private Role role;

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .nickname(nickname)
                .password(password)
                .email(email)
                .role(Role.USER).build();
    }
}

