package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.dtos.*;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

// controller + responsebody(모든 메서드에 )
@RestController
@RequestMapping("/member/rest")
public class MemberRestController {
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //    회원목록조회
    @GetMapping("/list")
    public ResponseEntity<?> memberList() {
        List<MemberListRes> memberListResList = memberService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "memberlist is found", memberListResList), HttpStatus.OK);
    }

    //    회원상세조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> memberDetail(@PathVariable Long id) {
        MemberDetailDto dto = memberService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "member is found", dto), HttpStatus.OK);
    }

//    회원가입
    @PostMapping("/create")
    public ResponseEntity<?> memberCreatePost(@RequestBody MemberCreateDto memberCreateDto) {
        Member member = memberService.save2(memberCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(), "member create is successful", member.getId()), HttpStatus.CREATED);
    }

//    비밀번호 변경
//    get:조회, post:등록 patch:부분수정, put:대체, delete:삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public ResponseEntity<?> memberUpdatePw(@RequestBody MemberUpdateDto memberUpdateDto) {
        memberService.MemberUpdatePw(memberUpdateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "change pw is successful", "OK"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "delete is successful", "OK"), HttpStatus.OK);
    }
}
