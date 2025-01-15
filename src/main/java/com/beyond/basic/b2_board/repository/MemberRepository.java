package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//SpringDataJpa가 되기 위해서는 JpaRepository를 상속해야하고, 상속시에 Entity명과 Entity타입을 명시한다.
//JpaRepository를 상속함으로서 JpaRepository의 주요기능(각종 CRUD기능 사전 구현) 상속.
public interface MemberRepository extends JpaRepository<Member,Long> {
//    save, findAll, findById는 사전에 구현.
//    그외에 다른 컬럼으로 조회할때는 findBy + 컬럼명 형식으로 선언만하면 실행시점에 구현.
    Optional<Member> findByEmail(String email);
}
