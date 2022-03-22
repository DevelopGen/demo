package Bayer.demo.service.user;

import Bayer.demo.domain.user.User;
import Bayer.demo.dto.user.UserLoginDto;
import Bayer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;

    public String login(UserLoginDto userLoginDto) {
        User findUser = userRepository.findByLoginId(userLoginDto.getLoginId())
                .filter(f -> f.getPassword().equals(userLoginDto.getPassword()))
                .orElse(null);
        if(findUser == null) return null;
        return findUser.getNickname();
    }
}
