package com.marondal.marondalgram.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.common.EncryptUtils;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(int id) {
		 User user = userRepository.findById(id).orElse(null);
		 return user;
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptString = EncryptUtils.md5(password);
		
		User user = userRepository.findByLoginIdAndPassword(loginId, encryptString).orElse(null);
		 
		return user;
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.countByLoginId(loginId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public User addUser(String loginId, String password, String name, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		User user = User.builder()
					.loginId(loginId)
					.password(encryptPassword)
					.name(name)
					.email(email)
					.build();
		
		return userRepository.save(user);
		
	}

}
