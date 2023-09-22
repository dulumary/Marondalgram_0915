package com.marondal.marondalgram.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/join-view")
	public String joinInput() {
		return "user/join";
	}

	@GetMapping("/login-view")
	public String loginInput() {
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		
		return "redirect:/user/login-view";
		
	}
}
