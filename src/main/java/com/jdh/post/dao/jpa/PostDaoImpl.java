package com.jdh.post.dao.jpa;

import com.jdh.post.dao.jpa.*;
import com.jdh.post.domain.Post;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component(PostDaoImpl.BEAN_NAME)
public class PostDaoImpl implements PostDao {

	public static final String BEAN_NAME = "postDao";

	private final static Charset ENCODING = StandardCharsets.UTF_8;

	@PersistenceContext
	private EntityManager em;

/*
	*//*
	* HIBERNATE
	* *//*
	public void persistPost(Post post) {
		em.persist(post);
	}

	public void persistPosts(List<Post> posts) {
		for (Post post : posts) {
			em.persist(post);
		}
	}

	public void updatePost(Post post) {
		Post existingVersionOfPost = em.find(Post.class, post.getId());
		existingVersionOfPost.setTitle(post.getTitle());
		existingVersionOfPost.setDescription(post.getDescription());
		existingVersionOfPost.setDueDate(post.getDueDate());
		em.persist(existingVersionOfPost);
	}*/

	public List<Post> getAllPersistedPosts() {
		return em.createQuery("SELECT a FROM Post a", Post.class).getResultList();
	}

/*	public Post getPostById(Integer postId) {
		return em.find(Post.class, postId);
	}

	public void deletePersistedPostById(Integer postId) {
		Post post = em.find(Post.class, postId);
		if (post != null) {
			em.remove(post);
		}
	}

	public void deleteAllPersistedPosts() {
		em.createQuery("DELETE from Post").executeUpdate();
	}

	public List<Post> getPostsByPostTitle(String postName) {
		return em.createQuery("SELECT a FROM Post a WHERE title = '" + postName + "'", Post.class).getResultList();
	}*/

}

