package com.gorl.demo.dto;

import com.gorl.demo.entity.User;

public class JWTAuthResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	private User user;
	
	public JWTAuthResponse() {	}
	
	/*
	 * public JWTAuthResponse(String accessToken, String tokenType) { super();
	 * this.accessToken = accessToken; this.tokenType = tokenType; }
	 * 
	 * public JWTAuthResponse(String accessToken, User user) { super();
	 * this.accessToken = accessToken; this.user = user; }
	 */

	public JWTAuthResponse(String accessToken, String tokenType, User user) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.user = user;
	}

	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
