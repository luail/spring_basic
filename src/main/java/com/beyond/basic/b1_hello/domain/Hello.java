package com.beyond.basic.b1_hello.domain;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

//@Getter
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 매개변수가 있는 생성자.
@Data //Getter, Setter, toString 메서드까지 포함하는 어노테이션
public class Hello {
    private String name;
//    @Setter //email변수에 관한 setter만 생성
    private String email;
//    private MultipartFile photo;
}
