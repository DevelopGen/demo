package Bayer.demo.service.board;

import Bayer.demo.repository.BoardRepository;
import Bayer.demo.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardMainService {

    private final BoardRepository boardRepository;

    public List<Board> findAll() {
        Sort sort = sortList();
        return boardRepository.findAll(sort);
    }

    private Sort sortList() { // id로 역순 정렬
        return Sort.by(Sort.Direction.DESC,"id");
    }
}
