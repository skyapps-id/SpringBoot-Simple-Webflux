package com.kltestbe.demo.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kltestbe.demo.auth.CustomUserDetails;

public class UserUtil {

	public static CustomUserDetails getCurrentLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		return userDetails;
	}
}
