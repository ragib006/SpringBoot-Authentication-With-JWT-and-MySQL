package com.zproject.demo.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zproject.demo.model.JwtResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private int userId;
	private String name;
	private String email;
	private String password;
	private String about;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}
	@Override
	public String getUsername() {
		
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	


}
