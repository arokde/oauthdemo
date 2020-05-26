package com.rocketsoftware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rocketsoftware.model.MyAppUser;

import com.rocketsoftware.repository.UserRepository;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  PasswordEncoder encoder;

	@Override
	public void createUser(MyAppUser user) {

		userRepository.save(user);

		System.out.println("***** User Saved  *****");
	}



}
