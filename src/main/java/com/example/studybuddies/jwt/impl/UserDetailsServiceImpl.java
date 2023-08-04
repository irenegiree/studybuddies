package com.example.studybuddies.jwt.impl;

import com.example.studybuddies.repository.UserRepository;
import com.example.studybuddies.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = userRepo.findByEmail(email);
		System.out.println("*************************************building start");
		try {
			return UserDetailsImpl.build(user);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
