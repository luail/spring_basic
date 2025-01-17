package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.domain.dtos.PostListRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000, nullable = false)
    private String contents;
    private Long memberId;
    @CreationTimestamp
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Post(String title, String contents, Long memberId) {
        this.title = title;
        this.contents = contents;
        this.memberId = memberId;
    }

    public PostListRes postListResFromEntity() {
        return new PostListRes(this.id, this.title);
    }
}
