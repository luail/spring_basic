package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.domain.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.domain.dtos.MemberListRes;
import com.beyond.basic.b2_board.domain.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/rest")
public class MemberRestController {
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //    회원목록조회
    @GetMapping("/list")
    public List<MemberListRes> memberList() {
        List<MemberListRes> memberListResList = memberService.findAll();
        return memberListResList;
    }

    //    회원상세조회
    @GetMapping("/detail/{id}")
    public MemberDetailDto memberDetail(@PathVariable Long id) {
            MemberDetailDto dto = memberService.findById(id);
            return dto;
    }

//    회원가입
    @PostMapping("/create")
    public String memberCreatePost(@RequestBody MemberCreateDto memberCreateDto) {
            memberService.save(memberCreateDto);
            return "OK";
    }

//    비밀번호 변경
//    get:조회, post:등록 patch:부분수정, put:대체, delete:삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public String memberUpdatePw(@RequestBody MemberUpdateDto memberUpdateDto) {
        memberService.MemberUpdatePw(memberUpdateDto);
        return "OK";
    }
}
