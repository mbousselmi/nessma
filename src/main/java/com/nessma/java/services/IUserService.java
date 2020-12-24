package com.nessma.java.services;

import java.util.List;

import com.nessma.java.models.User;

public interface IUserService   {
	
	User save(User user);
	List<User> getAllUsers();
	User findOne(String email);

}
