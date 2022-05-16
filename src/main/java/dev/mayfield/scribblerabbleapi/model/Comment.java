package dev.mayfield.scribblerabbleapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID of this comment out of all Comment objects
    private int commentID;
    // ID of the parent post out of all Post objects
    private int postID;
    // Author of this comment
    private String commentAuthor;
    // Date this comment was made
    private String date;

    // commentID of the parent of this comment, if applicable
    // private int replyID;
    // Location of the image associated with this comment
    private String imageURL;
}
