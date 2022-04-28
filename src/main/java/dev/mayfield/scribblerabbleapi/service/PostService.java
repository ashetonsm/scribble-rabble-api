package dev.mayfield.scribblerabbleapi.service;

import dev.mayfield.scribblerabbleapi.model.Post;
import dev.mayfield.scribblerabbleapi.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void createNewPost(Post post){
        postRepository.save(post);
    }

}
