package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.domain.dtos.PostDetailDto;
import com.beyond.basic.b2_board.domain.dtos.PostListRes;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@Entity
@AllArgsConstructor
// Builder어노테이션을 사용하여, 빌더패턴으로 엔티티의 생성자 구성.
// 빌더패턴의 장점 : 매개변수의 순서와 개수를 유연하게 세팅할 수 있다.
@Builder
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000, nullable = false)
    private String contents;
//    LAZY(지연로딩)로 설정 시 member객체를 사용하지 않는 한, member테이블로 쿼리 발생하지 않음.
//    이에 반해 EAGER(즉시로딩)타입으로 설정 시 사용하지 않아도 member테이블로 쿼리 발생.
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne에서는 default EAGER
    @JoinColumn(name = "member_id")
    private Member member;


    public PostListRes postListResFromEntity() {
        return PostListRes.builder().id(this.id).title(this.title).build();
    }

    public PostDetailDto toDetailDto(String email) {
        return PostDetailDto.builder().id(this.id).title(this.title).contents(this.contents).memberEmail(email).build();
    }
}
