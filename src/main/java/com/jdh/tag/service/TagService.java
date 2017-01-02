package com.jdh.tag.service;

import com.jdh.tag.dto.TagDto;

import java.util.List;

public interface TagService {

    public List<TagDto> getAllPersistedTags() throws Exception;
    public void persistTag(TagDto tagDto) throws Exception;

    public TagDto getTagById(Integer tagId) throws Exception;
    public void deleteTagById(Integer tagId);
    public List<TagDto> getTagsByTagName(String tagName);
    public List<TagDto> getTagsByBlogName(String blogName);

    public void updateTag(final TagDto tagDto);

}
