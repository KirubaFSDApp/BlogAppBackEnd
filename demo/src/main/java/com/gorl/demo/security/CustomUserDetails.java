package com.gorl.demo.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gorl.demo.entity.User;
import com.gorl.demo.repo.UserRepo;

@Service
public class CustomUserDetails implements UserDetailsService{

	private UserRepo userRepo;
	
	public CustomUserDetails(UserRepo userRepo) {
		
		this.userRepo = userRepo;
	}



	@Override
	public UserDetails loadUserByUsername(String usernameOEmail) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmailOrUsername(usernameOEmail, usernameOEmail)
			.orElseThrow(() -> new UsernameNotFoundException("User not found "+ usernameOEmail));
		
		Set<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), 
																	user.getPassword(),
																	authorities);
	}

}
