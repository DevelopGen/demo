package Bayer.demo.service.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.domain.board.Reply;
import Bayer.demo.repository.BoardRepository;
import Bayer.demo.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardViewService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public Board findBoard(Long boardId){
        Board findBoard = boardRepository.getById(boardId);
        increaseCount(findBoard);
        boardRepository.save(findBoard);
        return findBoard;
    }

    private void increaseCount(Board findBoard) { // 조회수 +1
        findBoard.setCount(findBoard.getCount() + 1);
    }
}
