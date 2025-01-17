package com.beyond.basic.b2_board.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 기본적으로 Entity는 상속이 불가능하여 MappedSuperClass 붙어야 Entity와의 상속관계가 성립가능.
// 상속을 할 때 Getter는 따라가지 않기 때문에 Getter도 같이 붙여준다.
@MappedSuperclass
@Getter
public class BaseTimeEntity {
    @CreationTimestamp
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
