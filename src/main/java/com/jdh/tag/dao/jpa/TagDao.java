package com.jdh.tag.dao.jpa;

import com.jdh.tag.domain.Tag;

import java.util.List;

public interface TagDao {

    public void persistTag(Tag tag);
    public List<Tag> getAllPersistedTags() throws Exception;
    public Tag getTagById(Integer tagId) throws Exception;
    public void deletePersistedTagById(Integer tagId);
    public void updateTag(Tag tag);
    public List<Tag> getTagsByTagName(String tagName);
    public List<Tag> getTagsByBlogName(String blogName);

}
