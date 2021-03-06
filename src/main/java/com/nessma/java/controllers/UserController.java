package com.nessma.java.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nessma.java.dto.UserDto;
import com.nessma.java.models.AuthenticationRequest;
import com.nessma.java.models.AuthenticationResponse;
import com.nessma.java.models.User;
import com.nessma.java.services.IUserService;
import com.nessma.java.services.impl.MyUserDetailsService;
import com.nessma.java.util.JwtUtil;

/**
 * 
 * @author malek Bousselmi 03/11/2020
 *
 */
@CrossOrigin
@RestController
public class UserController {

	@Autowired
	IUserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	JwtUtil jwtTokenUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
		try {
			User user = convertToEntity(userDto);
			User userSaved = userService.save(user);
			return ResponseEntity.ok(convertToDto(userSaved));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authentificationRequest) {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authentificationRequest.getUsername(), authentificationRequest.getPassword()));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authentificationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			User user = userService.findOne(userDetails.getUsername());
			return ResponseEntity
					.ok(new AuthenticationResponse(token, user.getEmail(), user.getUsername(), user.getRole()));

		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/hello")
	public String getHello() {
		return "Hello Malek";
	}

	private UserDto convertToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	private User convertToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

}
