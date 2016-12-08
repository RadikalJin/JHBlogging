package com.jdh.post.service;

import com.jdh.post.dao.jpa.PostDao;
import com.jdh.post.dao.jpa.PostDaoImpl;
import com.jdh.post.domain.Post;
import com.jdh.post.dto.PostDto;
import com.jdh.post.util.PostUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(PostServiceImpl.BEAN_NAME)
public class PostServiceImpl implements PostService {

	public static final String BEAN_NAME = "postService";

	@Resource(name = PostDaoImpl.BEAN_NAME)
	private PostDao postDao;

	static final Logger log = Logger.getLogger(PostServiceImpl.class);

	/*
	* HIBERNATE
	* */
	@Transactional
 	public List<PostDto> getAllPersistedPosts() throws Exception {
		try {
			List<PostDto> postDtos = PostUtil.convertPostDomainsToDtos(postDao.getAllPersistedPosts());
			log.info("Returned posts");
			return postDtos;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

/*	@Transactional
	public void persistPost(PostDto postDto) throws Exception {
		Post postCandidate = PostUtil.convertPostDtoToDomain(postDto);
		if (! postIsAlreadyPersisted(postCandidate)) {
			postDao.persistPost(postCandidate);
		}
	}

	@Transactional
	public PostDto getPostById(Integer postId) throws Exception {
		Post postById = postDao.getPostById(postId);
		if (postById == null) {
			return null;
		} else {
			return PostUtil.convertPostDomainToDto(postById);
		}
	}

	@Transactional
	public void deletePostById(Integer postId) {
		postDao.deletePersistedPostById(postId);
	}

	@Transactional
	public List<PostDto> getPostsByPostTitle(String postName) {
		return PostUtil.convertPostDomainsToDtos(postDao.getPostsByPostTitle(postName));
	}

	@Transactional
	public void updatePost(final PostDto postDto) {
		Post post = PostUtil.convertPostDtoToDomain(postDto);
		postDao.updatePost(post);
	}

	public Boolean postIsAlreadyPersisted(Post post) throws Exception {
		List<Post> allPersistedPosts = postDao.getAllPersistedPosts();
		Boolean matchFound = false;
		for (Post allPersistedPost : allPersistedPosts) {
			if (post.equals(allPersistedPost)) {
				matchFound = true;
			}
		}
		return matchFound;
	}*/
}