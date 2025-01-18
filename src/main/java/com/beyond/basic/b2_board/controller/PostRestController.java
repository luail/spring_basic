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

import javax.validation.Valid;
import java.util.List;

//RestController는 Controller의 모든 메서드에 ResponceBody 어노테이션이 들어간다.
//따라서 mvc설계에는 적합하지 않으며, RestApi설계에 적합하다.
@RestController
@RequestMapping("/post")
//아래 어노테이션은 의존성을 주입하는 방법 중 한가지로, 생성자주입 방식에 비해 다형성을 구현하지 못한다는 점이 차이점이다.
@RequiredArgsConstructor
public class PostRestController {
//    postService 의존성 주입.
    private final PostService postService;

//    등록
//    등록요청이기에 PostMapping을 사용해야한다.
    @PostMapping("/create")
//    valid와 NotEmpty 등 검증 어노테이션이 한쌍.
//    현재 valid 어노테이션이 붙어있는 이유는 json형식으로 사용자의 http요청을 입력받을 때,
//    postCreateDto객체에 가보면 title에 NotEmpty어노테이션이 붙어있다.
//    NotEmpty 즉, NotNull 조건을 검증하기 위해 이 메서드에 Valid어노테이션을 같이 사용해줘야 한다.
//    아래의 ResponseEntity를 재정의하여 사용하는 이유로는
//    front에게 상태코드와 상태메세지를 header부 뿐만 아니라 body부에도 넘겨주어
//    front가 좀 더 쉽게 분기처리가 가능해진다.
//    밑의 경우에 현재 예외처리가 없는데, 예외처리는 추후에 CommonExceptionHandler의
//    ControllerAdvice라는 어노테이션과 ExceptionHandler라는 어노테이션을 통해
//    서비스에서 Controller로 예외를 던질때 intercept하기에 따로 try-catch가 필요하지 않다.
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateDto postCreateDto) {
        postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(), "post create successful", "OK"), HttpStatus.CREATED);
    }

//    목록조회
//    목록조회는 단순한 조회요청이기 때문에 Get요청이다.
    @GetMapping("/list")
    public ResponseEntity<?> postList() {
        List<PostListRes> postListResList = postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "post list found successfully", postListResList), HttpStatus.OK);
    }

//    상세조회
//    상세조회는 PathVariable방식으로 사용자의 http요청을 처리하는데, 내가 필요한 값인 id를 중괄호안에 넣어준다
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id) {
        PostDetailDto postDetailDto = postService.postDetail(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(), "post detail found successfully", postDetailDto), HttpStatus.OK);
    }
}
