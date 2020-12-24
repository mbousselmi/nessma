package com.nessma.java.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nessma.java.Enum.ExceptionEnum;
import com.nessma.java.exceptions.NessmaException;
import com.nessma.java.models.User;
import com.nessma.java.repository.UserRepository;
import com.nessma.java.services.IUserService;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public User save(User user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new NessmaException(ExceptionEnum.VALID_ERROR);
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		return user.orElseThrow(() -> new NessmaException(ExceptionEnum.USER_NOT_FOUNT));

	}
}
