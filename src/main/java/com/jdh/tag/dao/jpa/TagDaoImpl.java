package com.jdh.tag.dao.jpa;

import com.jdh.tag.dao.jpa.TagDao;
import com.jdh.tag.domain.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component(TagDaoImpl.BEAN_NAME)
public class TagDaoImpl implements TagDao {

	public static final String BEAN_NAME = "tagDao";

	private final static Charset ENCODING = StandardCharsets.UTF_8;

	@PersistenceContext
	private EntityManager em;

	/*
	*
	* HIBERNATE
	*
	* */
	public void persistTag(Tag tag) {
		em.persist(tag);
	}

	public void persistTags(List<Tag> tags) {
		for (Tag tag : tags) {
			em.persist(tag);
		}
	}

	public void updateTag(Tag tag) {
		Tag existingVersionOfTag = em.find(Tag.class, tag.getId());
		existingVersionOfTag.setTagName(tag.getTagName());
		em.persist(existingVersionOfTag);
	}

	public List<Tag> getAllPersistedTags() {
		return em.createQuery("SELECT a FROM Tag a", Tag.class).getResultList();
	}

	public Tag getTagById(Integer tagId) {
		return em.find(Tag.class, tagId);
	}

	public void deletePersistedTagById(Integer tagId) {
		Tag tag = em.find(Tag.class, tagId);
		if (tag != null) {
			em.remove(tag);
		}
	}

	public void deleteAllPersistedTags() {
		em.createQuery("DELETE from Tag").executeUpdate();
	}

	public List<Tag> getTagsByTagName(String tagName) {
		return em.createQuery("SELECT a FROM Tag a WHERE TAG_NAME = '" + tagName + "'", Tag.class).getResultList();
	}

	public List<Tag> getTagsByBlogName(String blogName) {
		return em.createNativeQuery(
				"SELECT t.* FROM TAG AS t " +
						"INNER JOIN POST_TAG AS pt " +
						"ON t.TAG_ID = pt.TAG_ID " +
						"INNER JOIN POST AS p " +
						"ON pt.POST_ID = p.POST_ID " +
						"INNER JOIN BLOG AS b " +
						"ON p.BLOG_ID = b.BLOG_ID " +
						"WHERE b.BLOG_NAME = '" + blogName + "'", Tag.class).getResultList();
	}

}

