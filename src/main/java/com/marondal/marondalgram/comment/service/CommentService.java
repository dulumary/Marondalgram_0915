package com.marondal.marondalgram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.dto.CommentDetail;
import com.marondal.marondalgram.comment.repository.CommentRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	public int addComment(int userId, int postId, String content) {
		
		return commentRepository.insertComment(userId, postId, content);
	}
	
	public List<CommentDetail> getCommentList(int postId) {
		
		List<Comment> commentList = commentRepository.selectCommentList(postId);
		
		List<CommentDetail> commentDetailList = new ArrayList<>();
		for(Comment comment:commentList) {
			
			int userId = comment.getUserId();
			User user = userService.getUserById(userId);
			
			CommentDetail commentDetail = CommentDetail.builder()
													.id(comment.getId())
													.userId(comment.getUserId())
													.content(comment.getContent())
													.loginId(user.getLoginId())
													.build();
			
			commentDetailList.add(commentDetail);
			
		}
		
		return commentDetailList;
		
	}

}
