package com.jdh.tag.service;

import com.jdh.tag.dao.jpa.TagDao;
import com.jdh.tag.dao.jpa.TagDaoImpl;
import com.jdh.tag.domain.Tag;
import com.jdh.tag.dto.TagDto;
import com.jdh.tag.service.TagService;
import com.jdh.tag.util.TagUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(TagServiceImpl.BEAN_NAME)
public class TagServiceImpl implements TagService {

	public static final String BEAN_NAME = "tagService";

	@Resource(name = TagDaoImpl.BEAN_NAME)
	private TagDao tagDao;

	static final Logger log = Logger.getLogger(TagServiceImpl.class);

	/*
	* HIBERNATE
	* */
	@Transactional
 	public List<TagDto> getAllPersistedTags() throws Exception {
		try {
			List<TagDto> tagDtos = TagUtil.convertTagDomainsToDtos(tagDao.getAllPersistedTags());
			log.info("Returned tags");
			return tagDtos;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	@Transactional
	public void persistTag(TagDto tagDto) throws Exception {
		Tag tagCandidate = TagUtil.convertTagDtoToDomain(tagDto);
		if (! tagIsAlreadyPersisted(tagCandidate)) {
			tagDao.persistTag(tagCandidate);
		}
	}

	@Transactional
	public TagDto getTagById(Integer tagId) throws Exception {
		Tag tagById = tagDao.getTagById(tagId);
		if (tagById == null) {
			return null;
		} else {
			return TagUtil.convertTagDomainToDto(tagById);
		}
	}

	@Transactional
	public void deleteTagById(Integer tagId) {
		tagDao.deletePersistedTagById(tagId);
	}

	@Transactional
	public List<TagDto> getTagsByTagName(String tagName) {
		return TagUtil.convertTagDomainsToDtos(tagDao.getTagsByTagName(tagName));
	}

	@Transactional
	public List<TagDto> getTagsByBlogName(String blogName) {
		return TagUtil.convertTagDomainsToDtos(tagDao.getTagsByBlogName(blogName));
	}

	@Transactional
	public void updateTag(final TagDto tagDto) {
		Tag tag = TagUtil.convertTagDtoToDomain(tagDto);
		tagDao.updateTag(tag);
	}

	public Boolean tagIsAlreadyPersisted(Tag tag) throws Exception {
		for (Tag persistedTag : tagDao.getAllPersistedTags()) {
			if (tag.equals(persistedTag)) {
				return true;
			}
		}
		return false;
	}
}