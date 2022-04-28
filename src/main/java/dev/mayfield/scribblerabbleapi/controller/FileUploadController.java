package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.model.Post;
import dev.mayfield.scribblerabbleapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/files")
public class FileUploadController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/user-uploads";
    @Autowired
    PostService postService;

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
    public RedirectView upload(Model model, @RequestParam("file") MultipartFile file,
                               @RequestParam("author") String author) throws IOException {
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        String filename = file.getOriginalFilename();
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

        model.addAttribute("msg", "Successfully uploaded files " + filename);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000");

        return redirectView;
    }

    @PostMapping("/uploadByUser")
    public RedirectView upload(@RequestParam("file") MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        String filename = file.getOriginalFilename();
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000");
        return redirectView;
    }

    @GetMapping(value = "/test")
    public String fileTestMessage() {
        return "TESTED A GET";
    }

    @PostMapping(value = "/test")
    public String fileTestPost() {
        return "POSTED A THING";
    }
}