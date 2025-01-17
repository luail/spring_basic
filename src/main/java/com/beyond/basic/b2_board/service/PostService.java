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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void save(PostCreateDto postCreateDto) throws EntityNotFoundException, IllegalArgumentException {
        if (memberRepository.findById(postCreateDto.getMemberId()).isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 ID입니다.");
        }

        if (postCreateDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }

        if (postCreateDto.getContents().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        Post post = postCreateDto.toEntity();
        postRepository.save(post);
    }

    public List<PostListRes> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(p->p.postListResFromEntity()).collect(Collectors.toList());
    }

    public PostDetailDto postDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 게시글ID입니다."));
        Member member = memberRepository.findById(post.getMemberId()).orElseThrow(()->new EntityNotFoundException("없는 회원ID입니다."));
        return new PostDetailDto(post.getId(), post.getTitle(), post.getContents(), member.getEmail());
    }
}
