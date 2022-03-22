package Bayer.demo.service.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.domain.user.User;
import Bayer.demo.dto.board.BoardSaveDto;
import Bayer.demo.repository.BoardRepository;
import Bayer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPostingService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(BoardSaveDto boardSaveDto, String nickname) {
        User findUser = userRepository.findByNickname(nickname).orElse(null);
        Board board = boardSaveDto.toEntity(findUser);
        board.setAuth(findUser);
        boardRepository.save(board);
    }
}
