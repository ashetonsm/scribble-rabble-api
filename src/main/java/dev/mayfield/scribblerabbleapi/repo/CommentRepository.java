package dev.mayfield.scribblerabbleapi.repo;

import dev.mayfield.scribblerabbleapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Two main interfaces to explore are JpaRepository, CrudRepository

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> { }
