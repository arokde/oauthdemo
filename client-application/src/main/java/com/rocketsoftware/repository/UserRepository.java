package com.rocketsoftware.repository;

import org.springframework.data.repository.CrudRepository;

import com.rocketsoftware.model.MyAppUser;

public interface UserRepository extends CrudRepository<MyAppUser, String> {

	MyAppUser findByUsername(String username);
	MyAppUser findByEmail(String email);

}
