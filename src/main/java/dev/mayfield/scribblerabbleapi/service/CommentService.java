package dev.mayfield.scribblerabbleapi.service;

import dev.mayfield.scribblerabbleapi.model.Comment;
import dev.mayfield.scribblerabbleapi.model.Post;
import dev.mayfield.scribblerabbleapi.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void createNewComment(Comment comment){
        commentRepository.save(comment);
    }

}
