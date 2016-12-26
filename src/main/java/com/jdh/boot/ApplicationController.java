package com.jdh.boot;

import com.google.gson.Gson;
import com.jdh.blog.dto.BlogDto;
import com.jdh.blog.service.BlogService;
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
    BlogService blogService = (BlogService) context.getBean("blogService");
    PostService postService = (PostService) context.getBean("postService");
    UserService userService = (UserService) context.getBean("userService");


    /*
    *
    * USER
    *
    * */
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

    @RequestMapping(value = "/loginUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String loginUser(@RequestBody UserDto userDto) {
        try {
            UserDto matchingUser = userService.getUserByUsername(userDto.getUsername());
            if (matchingUser.getPassword().equals(userDto.getPassword())) {
                return new Response("OK", "").toJSON();
            } else {
                throw new Exception("Password does not match");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    /*
    *
    * BLOG
    *
    * */
    @RequestMapping(value = "/createBlog",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String createBlog(@RequestBody BlogDto blogDto) {
        try {
            blogService.persistBlog(blogDto);
            return new Response("OK", "").toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    @RequestMapping("/blogs")
    public String getAllBlogs() throws Exception {
        try {
            List<BlogDto> persisted = blogService.getAllPersistedBlogs();
            return new Response("OK", new Gson().toJson(persisted)).toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    /*
    *
    * POST
    *
    * */
    @RequestMapping(value = "/createPost",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String createPost(@RequestBody PostDto postDto) {
        try {
            postService.persistPost(postDto);
            return new Response("OK", "").toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    @RequestMapping(value = "/updatePost",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String updatePost(@RequestBody PostDto postDto) {
        try {
            postService.updatePost(postDto);
            return new Response("OK", "").toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    @RequestMapping(value = "/getPost",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String getPost(@RequestBody PostDto postDto) {
        try {
            PostDto postById = postService.getPostById(Integer.valueOf(postDto.getId()));
            return new Response("OK", new Gson().toJson(postById)).toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    @RequestMapping(value = "/getPostsByUsername",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public String getPostsByUsername(@RequestBody PostDto postDto) {
        try {
            List<PostDto> postsByUsername = postService.getPostsByUsername(postDto.getUserName());
            return new Response("OK", new Gson().toJson(postsByUsername)).toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

    @RequestMapping("/posts/{blogName}")
    public String getPostsByBlog(@PathVariable String blogName) throws Exception {
        try {
            List<PostDto> persistedPosts = postService.getPostsByBlogName(blogName);
            return new Response("OK", new Gson().toJson(persistedPosts)).toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage()).toJSON();
        }
    }

}
