package com.gorl.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gorl.demo.dto.LoginDto;
import com.gorl.demo.dto.RegisterDto;
import com.gorl.demo.entity.Role;
import com.gorl.demo.entity.User;
import com.gorl.demo.exception.BadRequestException;
import com.gorl.demo.repo.RoleRepo;
import com.gorl.demo.repo.UserRepo;
import com.gorl.demo.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	
	public AuthServiceImpl(AuthenticationManager authenticationManager, 
			UserRepo userRepo, RoleRepo roleRepo,PasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider) {
		
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto dto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}

	@Override
	public String register(RegisterDto dto) {

		if(userRepo.existsByUsername(dto.getUsername()))
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exist");
		
		if(userRepo.existsByEmail(dto.getEmail()))
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exist");
		
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role role = roleRepo.findByName("ROLE_USER").get();
		roles.add(role);
		
		user.setRoles(roles);
		
		userRepo.save(user);
		
		return "Register Successfull!..";
	}

}
