package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.domain.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.domain.dtos.MemberListRes;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberMemoryRepository memberMemoryRepository;

    public List<MemberListRes> findAll() {
//       List<Member> members = memberMemoryRepository.findAll();
//       List<MemberListRes> memberListRes = new ArrayList<>();
//       for (Member m : members) {
//           memberListRes.add(m.listFromEntity());
//       }
//       return memberListRes;
        return memberMemoryRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());
    }

    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException {
        if (memberMemoryRepository.findByEmail(memberCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (memberCreateDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }

        memberMemoryRepository.save(memberCreateDto.toEntity());
    }

    public MemberDetailDto findById(Long id) throws NoSuchElementException {
//        Optional<Member> optionalMember = memberMemoryRepository.findById(id);
//        Member member = optionalMember.orElseThrow(()->new NoSuchElementException("없는 ID입니다."));
//        MemberDetailDto dto = member.detailFromEntity();
//        return dto;
        return memberMemoryRepository.findById(id).
                orElseThrow(()->new NoSuchElementException("없는 ID입니다."))
                .detailFromEntity();
    }
}
