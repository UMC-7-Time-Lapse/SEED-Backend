package com.hackathon.TimeLapse.oauth;

import org.springframework.stereotype.Service;

import com.hackathon.TimeLapse.member.Member;
import com.hackathon.TimeLapse.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
	private final MemberRepository memberRepository;
	private final AuthTokensGenerator authTokensGenerator;
	private final RequestOAuthInfoService requestOAuthInfoService;

	public AuthTokens login(OAuthLoginParams params) {
		OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
		Long memberId = findOrCreateMember(oAuthInfoResponse);
		return authTokensGenerator.generate(memberId);
	}

	private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
		return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
			.map(Member::getId)
			.orElseGet(() -> newMember(oAuthInfoResponse));
	}

	private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
		Member member = Member.builder()
			.email(oAuthInfoResponse.getEmail())
			.nickname(oAuthInfoResponse.getNickname())
			.oAuthProvider(oAuthInfoResponse.getOAuthProvider())
			.build();

		return memberRepository.save(member).getId();
	}
}
