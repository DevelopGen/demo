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
public class UserPasswordEditDto {
    private String currentPassword;

    @NotBlank
    @NotNull
    @Length(min = 6)
    @Pattern(regexp = "^[a-zA-Z0-9!@*]+[a-zA-Z0-9!@*]*$")
    private String newPassword;

    private String newRePassword;
}
