package Bayer.demo.dto.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.domain.board.Reply;
import Bayer.demo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class ReplySaveDto {
    private String content;
    private Board board;
    private User user;

    public Reply toEntity(User user, Board board) {
        return Reply.builder()
                .content(content)
                .user(user)
                .board(board)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
