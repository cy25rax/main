package com.java.avito.controllers;

import com.java.avito.models.User;
import com.java.avito.security.models.AppError;
import com.java.avito.security.models.JwtRequest;
import com.java.avito.security.models.JwtResponse;
import com.java.avito.security.models.RegistrationUserDto;
import com.java.avito.security.utils.JwtTokenUtil;
import com.java.avito.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
	
	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping("/auth")
	public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
		}
		UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping("/registration")
	public ResponseEntity<?> createAuthToken(@RequestBody RegistrationUserDto registrationUserDto) {
		if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
			return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
		}
		if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
			return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setEmail(registrationUserDto.getEmail());
		user.setUsername(registrationUserDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
		userService.createUser(user);
		
		UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
}
