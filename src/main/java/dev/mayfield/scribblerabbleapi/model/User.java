package dev.mayfield.scribblerabbleapi.model;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String username;

    private String password;

    public User() {}
}
