package com.marondal.marondalgram.comment.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {
	
	public int insertComment(
			@Param("userId") int userId
			, @Param("postId") int postId
			, @Param("content") String content);

}
