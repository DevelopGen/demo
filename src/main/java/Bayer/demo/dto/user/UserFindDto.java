package Bayer.demo.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserFindDto {
    private String email;
    private String loginId;
}
