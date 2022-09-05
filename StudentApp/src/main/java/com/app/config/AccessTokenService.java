package com.app.config;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class AccessTokenService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public static final String ROLES = "ROLES";

    @Value("${jwt.secret}")
    private String secret;

    //retrieve name from token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    @SuppressWarnings("unchecked")
	public List<String> getRoles(String token) {
        return getClaimFromToken(token, claims -> (List) claims.get(ROLES));
     }

     public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
         final Claims claims = getAllClaimsFromToken(token);
         return claimsResolver.apply(claims);
     }
     //set secret key for retrieving information from token
     private Claims getAllClaimsFromToken(String token) {
         return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
     }

     //generate token for user
     public String generateToken(Authentication authentication) {
         final Map<String, Object> claims = new HashMap<>();
         final UserDetails user = (UserDetails) authentication.getPrincipal();

         final List<String> roles = authentication.getAuthorities()
                                                  .stream()
                                                  .map(GrantedAuthority::getAuthority)
                                                  .collect(Collectors.toList());

         claims.put(ROLES, roles);
         return generateToken(claims, user.getUsername());
     }
     
     //generate token string
     private String generateToken(Map<String, Object> claims, String subject) {
         return Jwts.builder()
                 .setClaims(claims)
                 .setSubject(subject)
                 .signWith(SignatureAlgorithm.HS512, secret).compact();
     }

     //validate token
     public Boolean validateToken(String token) {
         final String username = getUsernameFromToken(token);
         return username != null;
     }

}
