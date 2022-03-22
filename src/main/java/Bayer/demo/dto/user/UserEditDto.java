package Bayer.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@AllArgsConstructor
public class UserEditDto {
    @NotBlank
    @NotNull
    @Length(min = 2)
    @Pattern(regexp = "^[가-핳a-zA-Z0-9]*$")
    private String nickname;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String email;
}
