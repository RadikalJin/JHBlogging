package com.jdh.boot;

import com.jdh.post.dto.PostDto;
import com.jdh.post.service.PostService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    PostService postService = (PostService) context.getBean("postService");


    @RequestMapping("/posts")
    public List<PostDto> posts() throws Exception {
        List<PostDto> persistedPosts = postService.getAllPersistedPosts();
        return persistedPosts;
    }

}
