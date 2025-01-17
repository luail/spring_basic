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
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void save(PostCreateDto postCreateDto) throws EntityNotFoundException, IllegalArgumentException {
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
