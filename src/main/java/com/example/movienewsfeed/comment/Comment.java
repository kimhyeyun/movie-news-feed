/*
import com.example.movienewsfeed.comment.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime createDate;

    public Comment(CommentRequestDTO dto) {
        this.text = dto.getText();
        this.createDate = LocalDateTime.now();
    }

}
*/
