package Bayer.demo.service.user;

import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.UserEditDto;
import Bayer.demo.dto.user.UserPasswordEditDto;
import Bayer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEditService {

    private final UserRepository userRepository;
    private final UserSignupService userSignupService;

    public User roadUser(String nickname) {
        return userRepository.findByNickname(nickname).orElse(null);
    }

    public String editUserInfo(String nickname, UserEditDto userEditDto) {
        User findUser = userRepository.findByNickname(nickname).orElse(null);
        if (!findUser.getNickname().equals(userEditDto.getNickname())) userSignupService.validateDuplicateNickname(userEditDto.getNickname());
        if (!findUser.getEmail().equals(userEditDto.getEmail())) userSignupService.validateDuplicateEmail(userEditDto.getEmail());
        User editUser = ModifiedInfo(findUser, userEditDto);
        userRepository.save(editUser);
        return editUser.getNickname();
    }

    public void editPassword(String nickname, UserPasswordEditDto userPasswordEditDto) {
        if (!userPasswordEditDto.getNewPassword().equals(userPasswordEditDto.getNewRePassword())) throw new IllegalStateException("새 비밀번호가 동일하지 않습니다.");
        User findUser = userRepository.findByNickname(nickname).orElse(null);
        if (!findUser.getPassword().equals(userPasswordEditDto.getCurrentPassword())) throw new IllegalStateException("현재 비밀번호가 일치하지 않습니다.");
        if (findUser.getPassword().equals(userPasswordEditDto.getNewPassword())) throw new IllegalStateException("동일한 비밀번호로 변경할 수 없습니다.");
        User editUser = ModifiedInfo(findUser, userPasswordEditDto);
        userRepository.save(editUser);
    }

    private User ModifiedInfo(User findUser, UserEditDto userEditDto) {
        findUser.editNickname(userEditDto.getNickname());
        findUser.editEmail(userEditDto.getEmail());
        return findUser;
    }

    private User ModifiedInfo(User findUser, UserPasswordEditDto userPasswordEditDto) {
        findUser.editPassword(userPasswordEditDto.getNewPassword());
        return findUser;
    }
}
