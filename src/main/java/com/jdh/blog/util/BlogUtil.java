package com.jdh.blog.util;

import com.jdh.blog.domain.Blog;
import com.jdh.blog.dto.BlogDto;

import java.util.*;

public class BlogUtil {

    public static BlogDto convertBlogDomainToDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        if (blog.getId() != null) {
            blogDto.setId(String.valueOf(blog.getId()));
        }
        blogDto.setBlogName(blog.getBlogName());
        return blogDto;
    }

    public static List<BlogDto> convertBlogDomainsToDtos(List<Blog> blogDomains) {
        List<BlogDto> blogDtos = new ArrayList<>();
        for (Blog blogDomain : blogDomains) {
            blogDtos.add(convertBlogDomainToDto(blogDomain));
        }
        return blogDtos;
    }

    public static Blog convertBlogDtoToDomain(BlogDto blogDto) {
        Blog blog = new Blog();
        if (blogDto.getId() != null) {
            blog.setId(Integer.parseInt(blogDto.getId()));
        }
        blog.setBlogName(blogDto.getBlogName());
        return blog;
    }

    public static List<Blog> convertBlogDtosToDomains(List<BlogDto> blogDtos) {
        List<Blog> blogs = new ArrayList<>();
        for (BlogDto blogDto : blogDtos) {
            blogs.add(convertBlogDtoToDomain(blogDto));
        }
        return blogs;
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
