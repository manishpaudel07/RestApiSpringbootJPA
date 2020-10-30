package com.employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employees.model.User;

@Repository
public interface  StudentLoginRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findUserByUsername(String username);

	

}
