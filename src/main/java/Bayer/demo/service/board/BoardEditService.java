package Bayer.demo.service.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.dto.board.BoardEditDto;
import Bayer.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardEditService {

    private final BoardRepository boardRepository;

    public void editBoard(BoardEditDto boardEditDto){
        Board findBoard = boardRepository.getById(boardEditDto.getId());
        Board editBoard = Modified(findBoard, boardEditDto);
        boardRepository.save(editBoard);
    }

    private Board Modified(Board findBoard, BoardEditDto boardEditDto) {
        findBoard.editTitle(boardEditDto.getTitle());
        findBoard.editContent(boardEditDto.getContent());
        return findBoard;
    }
}
