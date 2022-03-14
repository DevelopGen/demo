package Bayer.demo.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginDto {
    public String loginId;
    public String password;
}
