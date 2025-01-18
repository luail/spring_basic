package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.domain.dtos.PostCreateDto;
import com.beyond.basic.b2_board.domain.dtos.PostDetailDto;
import com.beyond.basic.b2_board.domain.dtos.PostListRes;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b2_board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// 트랜잭셔널 잊지 말자!
// 트랜잭셔널 어노테이션을 통해 commit 및 rollback을 jpa에서 알아서 해준다.
@Transactional

//여기서 의존성주입을 postRepository와 memberRepository 두가지를 하고있는 이유는
//PostService에서 Member를 필요로 하기때문에 의존성을 주입하여 사용한다.
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

//    save에서 예외를 던지고 있는데, 이는 Controller에게 전파된다.
//    하지만 ExceptionHandler(@ControllerAdvice)가 모든 Controller를 모니터링하고 있어,
//    Service에서 예외가 처리되지 않고, ExceptionHandler에서 예외처리된다.
//    굳이 이렇게 예외처리를 다른곳에서 하는 이유로는
//    컨트롤러에서 모든 예외를 처리하면 코드의 중복 뿐 아니라, 예외또한 중복되기에
//    따로 CommonExceptionHandler를 만들어 코드의 중복을 피할 수 있다.
    public void save(PostCreateDto postCreateDto) throws EntityNotFoundException {
        Member member = memberRepository.findById(postCreateDto.getMemberId()).orElseThrow(()->new EntityNotFoundException("member is not found"));
        Post post = postCreateDto.toEntity(member);
        postRepository.save(post);
    }

    public List<PostListRes> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(p->p.postListResFromEntity()).collect(Collectors.toList());
    }

    public PostDetailDto postDetail(Long id) throws EntityNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("post is not found"));
        return post.toDetailDto(post.getMember().getEmail());
    }
}
