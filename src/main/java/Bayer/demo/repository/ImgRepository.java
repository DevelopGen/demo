package Bayer.demo.repository;

import Bayer.demo.domain.user.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImgRepository extends JpaRepository< ProfileImg, Long> {
    Optional<ProfileImg> findBydbImgName(String imgName);
}
