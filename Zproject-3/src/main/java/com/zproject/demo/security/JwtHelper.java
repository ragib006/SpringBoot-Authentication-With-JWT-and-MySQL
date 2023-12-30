package com.zproject.demo.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {
	
	
	 public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; 
	 
	 
	  public String getUsernameFromToken(String token) { 
	        return getClaimFromToken(token, Claims::getSubject); 
	    } 
	  
	  
	   public Date getExpirationDateFromToken(String token) { 
	        return getClaimFromToken(token, Claims::getExpiration); 
	    } 
	   
	   
	    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) { 
	        final Claims claims = getAllClaimsFromToken(token); 
	        return claimsResolver.apply(claims); 
	    } 
	      
		  
	    private Claims getAllClaimsFromToken(String token) { 
	        return Jwts 
	                .parserBuilder() 
	                .setSigningKey(getSignKey()) 
	                .build() 
	                .parseClaimsJws(token) 
	                .getBody(); 
	    } 
	    
	    private Key getSignKey() { 
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET); 
	        return Keys.hmacShaKeyFor(keyBytes); 
	    } 
	    
	    
	    private Boolean isTokenExpired(String token) { 
	       // return extractExpiration(token).before(new Date()); 
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    } 
	    
	    
	    public String generateToken(UserDetails userDetails) { 
	       
	        Map<String, Object> claims = new HashMap<>();
	        return doGenerateToken(claims, userDetails.getUsername());
	      
	    } 
	  
	  
	    private String doGenerateToken(Map<String, Object> claims, String subject) { 
	        return Jwts.builder() 
	                .setClaims(claims) 
	                .setSubject(subject) 
	                .setIssuedAt(new Date(System.currentTimeMillis())) 
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
	    } 
	  
	  
	  
	 
	    //validate token
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
	

	 
	  
	  
	
	
	

}
