package com.hackathon.TimeLapse.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.TimeLapse.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);
}
