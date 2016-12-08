package com.jdh.boot;

import com.google.gson.Gson;
import com.jdh.post.dto.PostDto;
import com.jdh.post.service.PostService;
import com.jdh.user.dto.UserDto;
import com.jdh.user.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    PostService postService = (PostService) context.getBean("postService");
    UserService userService = (UserService) context.getBean("userService");


    @RequestMapping(value = "/createUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String createUser(@RequestBody UserDto userDto) {
        try {
            userService.persistUser(userDto);

            return new Response("OK", "").toJSON();
        } catch (Exception e) {
            e.printStackTrace();

            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    @RequestMapping("/posts")
    public List<PostDto> posts() throws Exception {
        List<PostDto> persistedPosts = postService.getAllPersistedPosts();
        return persistedPosts;
    }

}
