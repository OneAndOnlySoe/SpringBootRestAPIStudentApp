package com.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.entity.User;
import com.app.json.UserDTO;

public interface IUserService extends UserDetailsService{
		
	//save data
	User saveUser(UserDTO user);
}
