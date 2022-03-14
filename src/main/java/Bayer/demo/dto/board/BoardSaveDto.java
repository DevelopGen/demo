package Bayer.demo.dto.board;


import Bayer.demo.domain.board.Board;
import Bayer.demo.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class BoardSaveDto {
    @NotNull
    private String title;

    @NotNull
    private String content;

    private Integer count;

    private User user;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .count(0)
                .user(user)
                .auth(user.getNickname())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public void setUser(User user) {
        this.user = user;
    }
}

