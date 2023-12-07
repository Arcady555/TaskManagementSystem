package ru.parfenov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.parfenov.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer>  {
    List<Comment> findByTaskId(int id);
}