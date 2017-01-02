package com.jdh.tag.util;

import com.jdh.tag.domain.Tag;
import com.jdh.tag.dto.TagDto;

import java.util.*;

public class TagUtil {

    public static TagDto convertTagDomainToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        if (tag.getId() != null) {
            tagDto.setId(String.valueOf(tag.getId()));
        }
        tagDto.setTagName(tag.getTagName());
        return tagDto;
    }

    public static List<TagDto> convertTagDomainsToDtos(List<Tag> tagDomains) {
        List<TagDto> tagDtos = new ArrayList<>();
        for (Tag tagDomain : tagDomains) {
            tagDtos.add(convertTagDomainToDto(tagDomain));
        }
        return tagDtos;
    }

    public static Tag convertTagDtoToDomain(TagDto tagDto) {
        Tag tag = new Tag();
        if (tagDto.getId() != null) {
            tag.setId(Integer.parseInt(tagDto.getId()));
        }
        tag.setTagName(tagDto.getTagName());
        return tag;
    }

    public static List<Tag> convertTagDtosToDomains(List<TagDto> tagDtos) {
        List<Tag> tags = new ArrayList<>();
        for (TagDto tagDto : tagDtos) {
            tags.add(convertTagDtoToDomain(tagDto));
        }
        return tags;
    }

    public static Calendar convertStringDateToSortableDate(String stringDate) {

        Map<String, Integer> months = new HashMap<>();
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);

        String[] processedDate = stringDate.split(" ");
        Integer monthNumeric = months.get(processedDate[0]);
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.set(2015, monthNumeric, Integer.parseInt(processedDate[1]));
        return releaseDate;
    }

    public static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

}
