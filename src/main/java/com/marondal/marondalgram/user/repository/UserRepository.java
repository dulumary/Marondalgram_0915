package com.marondal.marondalgram.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marondal.marondalgram.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// SELECT count(1) ...  WHERE `loginId` = #{loginId};
	public int countByLoginId(String loginId);
}
