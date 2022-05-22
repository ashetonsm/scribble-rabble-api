package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.model.Comment;
import dev.mayfield.scribblerabbleapi.model.Post;
import dev.mayfield.scribblerabbleapi.service.CommentService;
import dev.mayfield.scribblerabbleapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/user-uploads";
    public static String uploadDirectoryComments = System.getProperty("user.dir") + "/user-uploads/comments";

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    /**
     * Upload a file.
     *
     * @param model
     * @param file
     * @param author
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String upload(Model model, @RequestPart("file") MultipartFile file,
                         @RequestParam("author") String author) throws IOException {
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());

            // Make a new post
            Post post = new Post();
            post.setAuthor(author);
            post.setImage(fileNameAndPath.toString());
            postService.createNewPost(post);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }

    /**
     * Upload a file.
     *
     * @param model
     * @param file
     * @param author
     * @return
     * @throws IOException
     */
    @RequestMapping("/comment")
    public String comment(Model model, @RequestPart("file") MultipartFile file, @RequestParam("postID") Integer postID,
                         @RequestParam("author") String author) throws IOException {
        Path fileNameAndPath = Paths.get(uploadDirectoryComments, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());

            // Make a new comment
            Comment comment = new Comment();
            comment.setCommentAuthor(author);
            comment.setImageURL(fileNameAndPath.toString());
            comment.setPostID(postID);
            commentService.createNewComment(comment);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }

    /**
     * Get all Posts.
     *
     * @return
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}