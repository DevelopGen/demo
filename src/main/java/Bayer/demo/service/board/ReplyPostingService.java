package Bayer.demo.service.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.domain.board.Reply;
import Bayer.demo.domain.user.User;
import Bayer.demo.dto.board.ReplySaveDto;
import Bayer.demo.repository.BoardRepository;
import Bayer.demo.repository.ReplyRepository;
import Bayer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyPostingService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void save(ReplySaveDto replySaveDto, Long id, String nickname) {
        User findUser = userRepository.findByNickname(nickname).orElse(null);
        Board findBoard = boardRepository.getById(id);
        Reply reply = replySaveDto.toEntity(findUser,findBoard);
        reply.setAuth(findUser);
        replyRepository.save(reply);
    }
}
