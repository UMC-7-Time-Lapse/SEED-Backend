package com.hackathon.TimeLapse.oauth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final OAuthLoginService oAuthLoginService;

	@PostMapping("/kakao")
	public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
		return ResponseEntity.ok(oAuthLoginService.login(params));
	}
}