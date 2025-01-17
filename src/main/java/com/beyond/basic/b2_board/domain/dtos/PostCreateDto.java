package com.beyond.basic.b2_board.domain.dtos;

import com.beyond.basic.b2_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateDto {
    private String title;
    private String contents;
    private Long memberId;

    public Post toEntity() {
        return new Post(this.title, this.contents, this.memberId);
    }
}
