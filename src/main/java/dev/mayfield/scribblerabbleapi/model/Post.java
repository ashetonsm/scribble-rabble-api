package dev.mayfield.scribblerabbleapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Who made the post
    private String author;
    // Rename to imageURL
    private String image;
}
