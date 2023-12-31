package com.marondal.marondalgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.comment.dto.CommentDetail;
import com.marondal.marondalgram.comment.service.CommentService;
import com.marondal.marondalgram.common.FileManager;
import com.marondal.marondalgram.like.service.LikeService;
import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.dto.PostDetail;
import com.marondal.marondalgram.post.repository.PostRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private CommentService commentService;
	
	public int deletePost(int postId, int userId) {
		
		// 첨부된 파일 삭제 
		Post post = postRepository.selectPost(postId);
		
		if(post.getUserId() != userId) {
			return 0;
		}
		
		FileManager.removeFile(post.getImagePath());
		
		// 댓글 삭제
		commentService.deleteCommentByPostId(postId);
		
		// 좋아요 삭제
		likeService.deleteLikeByPostId(postId);
		
		return postRepository.deletePost(postId);
		
	}
	
	
	public int addPost(int userId, String content, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		return postRepository.insertPost(userId, content, imagePath);
		
	}
	
	public List<PostDetail> getPostList(int loginUserId, Integer targetUserId) {
	
		List<Post> postList = postRepository.selectPostList(targetUserId);
		List<PostDetail> postDetailList = new ArrayList<>();
		for(Post post:postList) {
			
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			// 좋아요 개수 조회 
			int likeCount = likeService.countLike(post.getId());
			boolean isLike = likeService.isLike(post.getId(), loginUserId);
			
			List<CommentDetail> commentList = commentService.getCommentList(post.getId());
			
			PostDetail postDetail = PostDetail.builder()
									.id(post.getId())
									.userId(userId)
									.content(post.getContent())
									.imagePath(post.getImagePath())
									.loginId(user.getLoginId())
									.likeCount(likeCount)
									.isLike(isLike)
									.commentList(commentList)
									.build();
			
			postDetailList.add(postDetail);
		}
		
		return postDetailList;
		
	}

}
