package Bayer.demo.repository;

import Bayer.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
}
