package Bayer.demo.service;

import Bayer.demo.dao.UserRepository;
import Bayer.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(User user) {
        validateDuplicateId(user.getLoginId());
        validateDuplicateNickname(user.getNickname());
        validateDuplicateEmail(user.getEmail());
        return userRepository.save(user).getId();
    }

    public Optional<User> findLoginId(String loginId){
        return userRepository.findByLoginId(loginId);
    }

    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    private void validateDuplicateId(String loginId) throws IllegalStateException{
        userRepository.findByLoginId(loginId)
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 아이디입니다.");});
    }

    private void validateDuplicateNickname(String nickname) throws IllegalStateException{
        userRepository.findByNickname(nickname)
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 닉네임입니다.");});
    }

    private void validateDuplicateEmail(String email) throws IllegalStateException{
        userRepository.findByEmail(email)
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 이메일입니다.");});
    }
}
