package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.dtos.CommonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/response/entity")
public class ReponseEntityContoller {
//    case1. @ResponseStatus 어노테이션 사용
    @GetMapping("/annotation1")
    @ResponseStatus(HttpStatus.OK)
    public String anntation1() {
        return "ok";
    }

    @PostMapping("/annotation2")
    @ResponseStatus(HttpStatus.CREATED)
    public String anntation2() {
        return "ok";
    }

//    case2. 메서드 체이닝 방식 : ResponseEntity의 클래스 사용
    @GetMapping("chainning1")
    public ResponseEntity<Member> chainning1() {
        Member member = new Member("hongildong", "ho@naver.com", "12341234");
//        header부에 200 OK, body에 member형태의 json
//        return ResponseEntity.ok(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

//    case3. ReponseEntity 객체를 직접 생성하여 custom하는 방식.
    @GetMapping("/custom1")
//    Object자리에 Member, ?도 가능
    public ResponseEntity<Object> custom1() {
        Member member = new Member("hongildong", "ho@naver.com", "12341234");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

//    제일 자주 쓰는 방식.
    @GetMapping("/custom2")
    public ResponseEntity<?> custom2() {
        Member member = new Member("hongildong", "ho@naver.com", "12341234");
//        header : 상태코드 + 상태메시지
//        body : 상태코드 + 상태메시지, 객체
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "member is found", member), HttpStatus.OK);
    }
}
