package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.domain.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.domain.dtos.MemberListRes;
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
//JPA의 엔티티매니저에게 객체를 위임하려면 @Entity 어노테이션 필요.
@Entity
public class Member {
    @Id //pk설정
//    identity : auto_increment설정(AUTO는 jpa에게 적절한 전략을 위임하는 것.)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    String은 별다른 설정이 없을 경우 varchar(255)로 DB컬럼 설정. 변수명==컬럼명 으로 변환.
    private String name;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
//    @Column(name = "pw") 이렇게 할수는 있으나 컬럼명과 변수명을 일치시키는것이 개발의 혼선을 줄일 수 있음.
    private String password;

//    java에서 캐멀케이스 사용시 DB에는 created_time으로 컬럼이 변환된다.
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public MemberListRes listFromEntity() {
        return new MemberListRes(this.id, this.name, this.email);
    }

    public MemberDetailDto detailFromEntity() {
        return new MemberDetailDto(this.name, this.email, this.password);
    }

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updatePw(String newPassword) {
        this.password = newPassword;
    }
}
