package com.jdh.post.util;

import com.jdh.post.domain.Post;
import com.jdh.post.dto.PostDto;

import java.util.*;

public class PostUtil {

    public static PostDto convertPostDomainToDto(Post post) {
        PostDto postDto = new PostDto();
        if (post.getId() != null) {
            postDto.setId(String.valueOf(post.getId()));
        }
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setDueDate(post.getDueDate());
        return postDto;
    }

    public static List<PostDto> convertPostDomainsToDtos(List<Post> postDomains) {
        List<PostDto> postDtos = new ArrayList<>();
        for (Post postDomain : postDomains) {
            postDtos.add(convertPostDomainToDto(postDomain));
        }
        return postDtos;
    }

    public static Post convertPostDtoToDomain(PostDto postDto) {
        Post post = new Post();
        if (postDto.getId() != null) {
            post.setId(Integer.parseInt(postDto.getId()));
        }
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDueDate(postDto.getDueDate());
        return post;
    }

    public static List<Post> convertPostDtosToDomains(List<PostDto> postDtos) {
        List<Post> posts = new ArrayList<>();
        for (PostDto postDto : postDtos) {
            posts.add(convertPostDtoToDomain(postDto));
        }
        return posts;
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