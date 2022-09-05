package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.AccessTokenService;
import com.app.json.JwtResponse;
import com.app.json.UserDTO;
import com.app.service.impl.UserService;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AccessTokenService tokenService;

	@Autowired
	private UserService userService;

	@PostMapping("/auth/token")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) throws Exception {

		final Authentication auth = authenticate(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		SecurityContextHolder.getContext().setAuthentication(auth);

		return ResponseEntity.ok(new JwtResponse(tokenService.generateToken(auth)));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userService.saveUser(user));
	}

	private Authentication authenticate(String username, String password) {
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

	}

}
