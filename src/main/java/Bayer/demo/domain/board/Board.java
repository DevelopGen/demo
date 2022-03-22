package Bayer.demo.domain.board;

import Bayer.demo.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity(name = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    private int count;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false)
    private String auth;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void setCount(int count){this.count = count;}
    public void setAuth(User user){this.auth = user.getNickname();}
    public void addReply(Reply reply){this.replies.add(reply);}
    public void editTitle(String title){this.title = title;}
    public void editContent(String content){this.content = content;}
}
