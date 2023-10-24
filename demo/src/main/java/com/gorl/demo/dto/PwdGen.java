package com.gorl.demo.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PwdGen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println(encoder.encode("Ppts@123"));
		System.out.println(encoder.encode("admin123"));
	}

}
