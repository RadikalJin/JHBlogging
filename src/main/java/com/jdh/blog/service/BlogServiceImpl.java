package com.jdh.blog.service;

import com.jdh.blog.dao.jpa.BlogDao;
import com.jdh.blog.dao.jpa.BlogDaoImpl;
import com.jdh.blog.domain.Blog;
import com.jdh.blog.dto.BlogDto;
import com.jdh.blog.service.*;
import com.jdh.blog.service.BlogService;
import com.jdh.blog.util.BlogUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(com.jdh.blog.service.BlogServiceImpl.BEAN_NAME)
public class BlogServiceImpl implements BlogService {

	public static final String BEAN_NAME = "blogService";

	@Resource(name = BlogDaoImpl.BEAN_NAME)
	private BlogDao blogDao;

	static final Logger log = Logger.getLogger(com.jdh.blog.service.BlogServiceImpl.class);

	/*
	* HIBERNATE
	* */
	@Transactional
 	public List<BlogDto> getAllPersistedBlogs() throws Exception {
		try {
			List<BlogDto> blogDtos = BlogUtil.convertBlogDomainsToDtos(blogDao.getAllPersistedBlogs());
			log.info("Returned blogs");
			return blogDtos;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	@Transactional
	public void persistBlog(BlogDto blogDto) throws Exception {
		Blog blogCandidate = BlogUtil.convertBlogDtoToDomain(blogDto);
		if (! blogIsAlreadyPersisted(blogCandidate)) {
			blogDao.persistBlog(blogCandidate);
		}
	}

	@Transactional
	public BlogDto getBlogById(Integer blogId) throws Exception {
		Blog blogById = blogDao.getBlogById(blogId);
		if (blogById == null) {
			return null;
		} else {
			return BlogUtil.convertBlogDomainToDto(blogById);
		}
	}

	@Transactional
	public void deleteBlogById(Integer blogId) {
		blogDao.deletePersistedBlogById(blogId);
	}

	@Transactional
	public List<BlogDto> getBlogsByBlogName(String blogName) {
		return BlogUtil.convertBlogDomainsToDtos(blogDao.getBlogsByBlogName(blogName));
	}

	@Transactional
	public void updateBlog(final BlogDto blogDto) {
		Blog blog = BlogUtil.convertBlogDtoToDomain(blogDto);
		blogDao.updateBlog(blog);
	}

	public Boolean blogIsAlreadyPersisted(Blog blog) throws Exception {
		List<Blog> allPersistedBlogs = blogDao.getAllPersistedBlogs();
		Boolean matchFound = false;
		for (Blog allPersistedBlog : allPersistedBlogs) {
			if (blog.equals(allPersistedBlog)) {
				matchFound = true;
			}
		}
		return matchFound;
	}
}