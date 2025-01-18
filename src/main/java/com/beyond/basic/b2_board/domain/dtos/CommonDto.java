package com.beyond.basic.b2_board.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonDto {
    private int status_code; // 상태코드
    private String status_massage; // 상태메시지
    private Object result; // 변수의 타입을 Object로 두어 어떤 타입이 오더라도 커버가능.
}
