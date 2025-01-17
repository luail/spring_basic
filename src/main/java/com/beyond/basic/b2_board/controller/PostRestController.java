package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.dtos.CommonDto;
import com.beyond.basic.b2_board.domain.dtos.PostCreateDto;
import com.beyond.basic.b2_board.domain.dtos.PostDetailDto;
import com.beyond.basic.b2_board.domain.dtos.PostListRes;
import com.beyond.basic.b2_board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;

//    등록
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDto postCreateDto) {
        postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(), "post create successful", "OK"), HttpStatus.CREATED);
    }

//    목록조회
    @GetMapping("/list")
    public ResponseEntity<?> postList() {
        List<PostListRes> postListResList = postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "post list found successfully", postListResList), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id) {
        PostDetailDto postDetailDto = postService.postDetail(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "post detail found successfully", postDetailDto), HttpStatus.OK);
    }
}
