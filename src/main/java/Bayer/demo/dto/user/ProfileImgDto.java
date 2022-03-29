package Bayer.demo.dto.user;

import Bayer.demo.domain.user.ProfileImg;
import Bayer.demo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileImgDto {

    private String imgName;

    private String dbImgName;

    private String imgPath;

    private User user;

    public ProfileImg toEntity() {
        return ProfileImg.builder().
                imgName(imgName).
                dbImgName(dbImgName).
                imgPath(imgPath).
                user(user).build();
    }
}
