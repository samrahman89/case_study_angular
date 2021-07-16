package com.abdul.library.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdul.library.books.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUsername(String username);

}
