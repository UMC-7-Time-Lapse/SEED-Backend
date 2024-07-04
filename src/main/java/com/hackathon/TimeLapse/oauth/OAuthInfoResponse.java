package com.hackathon.TimeLapse.oauth;

public interface OAuthInfoResponse {
	String getEmail();
	String getNickname();
	OAuthProvider getOAuthProvider();
}
