package com.beyond.basic.b1_hello.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentReqDto {
    private String name;
    private String email;
    private List<Score> scores;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Score {
        private String subject;
        private Integer point;
    }
}
