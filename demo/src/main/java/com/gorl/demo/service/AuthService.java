package com.gorl.demo.service;

import com.gorl.demo.dto.LoginDto;
import com.gorl.demo.dto.RegisterDto;

public interface AuthService {

	String login(LoginDto dto);
	String register(RegisterDto dto);
}
