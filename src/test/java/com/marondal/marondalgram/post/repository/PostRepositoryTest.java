package com.marondal.marondalgram.post.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marondal.marondalgram.post.domain.Post;


@SpringBootTest
class PostRepositoryTest {
	
	@Autowired
	private PostRepository postRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void testPostListByIdList() {
		
		List<Integer> idList = new ArrayList<>();
		
		idList.add(4);
		idList.add(5);
		idList.add(6);
		idList.add(7);
		List<Post> postList = postRepository.selectPostByIdList(idList);
		
		logger.info(postList.toString());
		
	}

}
