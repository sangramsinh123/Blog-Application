package com.springboot.blog.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> 
				new UsernameNotFoundException("User Not Found Exception" + usernameOrEmail));
		
		Set<GrantedAuthority> authorities = user.getRoles()
					.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
