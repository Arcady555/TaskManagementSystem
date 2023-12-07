package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.CommentDto;
import ru.parfenov.model.Comment;
import ru.parfenov.model.Task;
import ru.parfenov.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final TaskService taskService;

    public List<CommentDto> findByTaskId(int taskId) {
        List<Comment> list = repository.findByTaskId(taskId);
        List<CommentDto> listDto = new ArrayList<>();
        for (Comment comment : list) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setTaskId(comment.getTask().getId());
            commentDto.setText(comment.getText());
            listDto.add(commentDto);
        }
        return listDto;
    }

    public Comment create(int taskId, String text) {
        Task task = taskService.findById(taskId);
        if (!"Задание не найдено!".equals(task.getDescription())) {
            Comment comment = new Comment();
            comment.setTask(task);
            comment.setText(text);
            return repository.save(comment);
        } else {
            return null;
        }
    }

    public void delete(int commentId) {
        Optional<Comment> comment = repository.findById(commentId);
        comment.ifPresent(repository::delete);
    }
}