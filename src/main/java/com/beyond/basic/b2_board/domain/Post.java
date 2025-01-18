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
//Entity라는 어노테이션을 통해 이 클래스가 Entity라는것을 jpaEntityManager가 알 수 있다.
@Entity
@AllArgsConstructor
// Builder어노테이션을 사용하여, 빌더패턴으로 엔티티의 생성자 구성.
// 빌더패턴의 장점 : 매개변수의 순서와 개수를 유연하게 세팅할 수 있다.
// 빌더어노테이션은 AllArgsConstructor어노테이션과 한쌍으로 이루어 져야한다.
// 만약 내가 Post에서 postListRes를 만들고 싶다면
// postListRes도 빌더가 있어야한다.
// 이유는 빌더가 생성자를 대신하는 개념이기에, post에서 postListRes를 생성하므로,
// postListRes에게 Builder가 있어야 한다.

//밑에 있는 BaseTimeEntity는 updateTime와 createTime은 대부분
//모든 entity에서 사용을 하게 되는데 코드의 중복을 피하기위해
//BaseTimeEntity라는 클래스를 만들어 상속하게 되면 코드의 중복을 제거할 수 있다.
@Builder
public class Post extends BaseTimeEntity {
//    Id로 PK를 설정한다.
    @Id
//    GeneratedValue로 auto_increment 생성.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000, nullable = false)
    private String contents;
//    LAZY(지연로딩)로 설정 시 member객체를 사용하지 않는 한, member테이블로 쿼리 발생하지 않음.
//    항상 LAZY를 사용한다고 생각해도 된다.(굳이 안쓰는 member객체를 조회시 마다 member테이블로 쿼리를 날릴 필요가 없기에.)
//    이에 반해 EAGER(즉시로딩)타입으로 설정 시 사용하지 않아도 member테이블로 쿼리 발생.
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne에서는 default EAGER (ManyToOne인 이유는 Post의 입장에선 Member와 N:1관계이기에.)
    @JoinColumn(name = "member_id") // JoinColumn을 통해 fk설정을 했다고 생각하면 됨.
//    Post의 클래스변수가 객체인 member인데, DB에서는 객체라는 개념이 없음.
//    따라서 JoinColumn에 name=member_id로 DB의 컬럼명을 전달함과 동시에 Fk관계인것을 명시적으로 표현했다.
    private Member member;


    public PostListRes postListResFromEntity() {
        return PostListRes.builder().id(this.id).title(this.title).build();
    }

//    email을 매개변수로 받아, email값 세팅.
    public PostDetailDto toDetailDto(String email) {
        return PostDetailDto.builder().id(this.id).title(this.title).contents(this.contents).memberEmail(email).build();
    }
}
