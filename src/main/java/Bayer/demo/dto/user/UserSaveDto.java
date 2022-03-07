package Bayer.demo.dto.user;

import Bayer.demo.domain.user.Role;
import Bayer.demo.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
public class UserSaveDto {
    @NotBlank
    @NotNull
    @Length(min = 5)
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9]*$")
    private String loginId;

    @NotBlank
    @NotNull
    @Length(min = 2)
    @Pattern(regexp = "^[가-핳a-zA-Z0-9]*$")
    private String nickname;

    @NotBlank
    @NotNull
    @Length(min = 6)
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9!@*]*$")
    private String password;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
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

