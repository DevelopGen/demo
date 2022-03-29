package Bayer.demo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity(name = "profileimg")
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILEIMG_ID")
    private Long id;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String dbImgName;

    @Column(nullable = false)
    private String imgPath;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

}
