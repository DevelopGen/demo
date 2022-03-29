package Bayer.demo.config;

import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.ProfileImgDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImgSet {

    public ProfileImgDto ProfileImgUpload(MultipartFile file, User user) {

        if (!file.isEmpty()) {

            String imgPath = "C:/Users/재민/Desktop/demo/src/main/resources/static/img/profileimg/";
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            String dbImgName = user.getId() + "_profileImg";

            File saveFile = new File(imgPath + dbImgName + "." + extension);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }

            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            log.info("user = {}", user.getNickname());
                log.info("name = {}", file.getOriginalFilename());
                log.info("extension = {}", extension);
                log.info("dbname = {}", dbImgName);
                log.info("path = {}", imgPath);

            return ProfileImgDto.builder()
                    .imgName(file.getOriginalFilename())
                    .dbImgName(dbImgName)
                    .imgPath("img/profileimg/" + dbImgName + "." + extension)
                    .user(user).build();
        }

        return null;
    }
}
