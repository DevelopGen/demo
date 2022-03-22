package Bayer.demo.service.board;

import Bayer.demo.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyDeleteService {

    private final ReplyRepository replyRepository;

    public void deleteReply(Long id){
        replyRepository.deleteById(id);
    }
}
