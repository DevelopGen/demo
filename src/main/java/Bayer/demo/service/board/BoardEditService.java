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
        return Board.builder()
                .id(findBoard.getId()) // <- 유지
                .count(findBoard.getCount())
                .user(findBoard.getUser())
                .createdDate(findBoard.getCreatedDate())
                .auth(findBoard.getAuth())
                .title(boardEditDto.getTitle()) // <- 변경
                .content(boardEditDto.getContent())
                .build();
    }
}
