package Bayer.demo.service.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.domain.user.User;
import Bayer.demo.dto.board.BoardSaveDto;
import Bayer.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPostingService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveDto boardSaveDto, User user) {
        boardSaveDto.setUser(user);
        Board board = boardSaveDto.toEntity();
        boardRepository.save(board);
    }
}
