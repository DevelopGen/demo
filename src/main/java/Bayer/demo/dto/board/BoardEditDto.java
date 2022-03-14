package Bayer.demo.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BoardEditDto {
    private Long id;
    private String title;
    private String content;

}
