package com.jdh.blog.dao.jpa;

import com.jdh.blog.dao.jpa.BlogDao;
import com.jdh.blog.domain.Blog;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component(BlogDaoImpl.BEAN_NAME)
public class BlogDaoImpl implements BlogDao {

	public static final String BEAN_NAME = "blogDao";

	private final static Charset ENCODING = StandardCharsets.UTF_8;

	@PersistenceContext
	private EntityManager em;

	/*
	*
	* HIBERNATE
	*
	* */
	public void persistBlog(Blog blog) {
		em.persist(blog);
	}

	public void persistBlogs(List<Blog> blogs) {
		for (Blog blog : blogs) {
			em.persist(blog);
		}
	}

	public void updateBlog(Blog blog) {
		Blog existingVersionOfBlog = em.find(Blog.class, blog.getId());
		existingVersionOfBlog.setBlogName(blog.getBlogName());
		em.persist(existingVersionOfBlog);
	}

	public List<Blog> getAllPersistedBlogs() {
		return em.createQuery("SELECT a FROM Blog a", Blog.class).getResultList();
	}

	public Blog getBlogById(Integer blogId) {
		return em.find(Blog.class, blogId);
	}

	public void deletePersistedBlogById(Integer blogId) {
		Blog blog = em.find(Blog.class, blogId);
		if (blog != null) {
			em.remove(blog);
		}
	}

	public void deleteAllPersistedBlogs() {
		em.createQuery("DELETE from Blog").executeUpdate();
	}

	public List<Blog> getBlogsByBlogName(String blogName) {
		return em.createQuery("SELECT a FROM Blog a WHERE BLOG_NAME = '" + blogName + "'", Blog.class).getResultList();
	}

}

