package dev.mayfield.scribblerabbleapi.repo;

import dev.mayfield.scribblerabbleapi.model.Post;
import dev.mayfield.scribblerabbleapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Two main interfaces to explore are JpaRepository, CrudRepository

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> { }
