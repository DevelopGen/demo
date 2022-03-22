package Bayer.demo.service.user;

import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.UserFindDto;
import Bayer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFindService {

    private final UserRepository userRepository;

    public String findUserId(String email){
        User findUser = userRepository.findByEmail(email).orElse(null);
        if (findUser == null) throw new IllegalStateException("존재하지 않는 계정 정보입니다.");
        return findUser.getLoginId();
    }

    public void findUserPw(UserFindDto userFindDto) {
        User findUser = userRepository.findByLoginId(userFindDto.getLoginId())
                .filter(f -> f.getEmail().equals(userFindDto.getEmail()))
                .orElse(null);
        if (findUser == null) {
            throw new IllegalStateException("계정 정보가 일치하지 않습니다.");
        }
    }
}
