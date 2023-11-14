package com.marondal.marondalgram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@RequestMapping("/user")
@RestController
@Validated
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpSession session) {
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			// 로그인 성공
			resultMap.put("result", "success");
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			
		} else {
			// 로그인 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
	}
	
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> duplicateId(@RequestParam("loginId") String loginId) {
		
		boolean isDuplicate = userService.isDuplicateId(loginId);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		if(isDuplicate) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		
		return resultMap;
		
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@Size(min=4, max=8) @RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @Email @RequestParam("email") String email) {
		
		
		
		User user = userService.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}
