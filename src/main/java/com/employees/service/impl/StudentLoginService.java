package com.employees.service.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employees.model.LoginSecurity;
import com.employees.model.User;
import com.employees.repository.StudentLoginRepository;


public class StudentLoginService implements UserDetailsService {

	
	@Autowired
	private StudentLoginRepository studentLoginRepository;
	@Override
	public UserDetails loadUserByUsername(String username)  {
		Optional<User> user =studentLoginRepository.findUserByUsername(username);
		
		User user2=user.orElseThrow(()->new UsernameNotFoundException("username doesnot exit"));
		return new LoginSecurity(user2);
	}

}
