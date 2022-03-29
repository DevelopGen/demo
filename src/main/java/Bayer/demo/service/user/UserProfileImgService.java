package Bayer.demo.service.user;

import Bayer.demo.domain.user.ProfileImg;
import Bayer.demo.dto.user.ProfileImgDto;
import Bayer.demo.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileImgService {

    private final ImgRepository imgRepository;

    @Transactional
    public void saveImg(ProfileImgDto profileImgDto) {
        if (imgRepository.findBydbImgName(profileImgDto.getDbImgName()).isPresent()){
            imgRepository.deleteById(imgRepository.findBydbImgName(profileImgDto.getDbImgName()).get().getId());
        }
        imgRepository.save(profileImgDto.toEntity());
    }

    @Transactional
    public ProfileImgDto getImg(Long id) {
        ProfileImg img = imgRepository.getById(id);

        return ProfileImgDto.builder().
                imgName(img.getImgName()).
                dbImgName(img.getDbImgName()).
                imgPath(img.getImgPath()).build();
    }
}
