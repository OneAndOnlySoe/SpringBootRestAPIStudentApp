package com.app.json;

import lombok.Data;

import java.io.Serializable;
@Data
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String token;

}