//package com.nessma.java.security.JWT;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtProvider {
//
//	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
//	
//	@Value("${jwt.secret}")
//	private String jwtSecret;
//	@Value("${jwt.expiration}")
//	private int jwtExpiration;
//
//	public String generate(Authentication authentication) {
//
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
//				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//	}
//
////	public boolean validate(String token) {
////		try {
////			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
////			return true;
////		} catch (Exception e) {
////			logger.error("JWT Authentication Failed");
////		}
////		return false;
////	}
//
//	public String getUserAccount(String token) {
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//	}
//
//}