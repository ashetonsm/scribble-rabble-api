package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.model.Post;
import dev.mayfield.scribblerabbleapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    @CrossOrigin
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

}
