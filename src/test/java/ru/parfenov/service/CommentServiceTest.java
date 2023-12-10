package ru.parfenov.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.parfenov.dto.CommentDto;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;
import ru.parfenov.model.Comment;
import ru.parfenov.model.Task;
import ru.parfenov.repository.CommentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceTest {

    @MockBean
    private CommentRepository repository;

    @MockBean
    private TaskService taskService;

    @Test
    void findByTaskId() {
        CommentService service = new CommentService(repository, taskService);
        Comment comment = new Comment(11, null, "Comment");
        Task task = new Task(
                1, null, "Task", Status.IN_WAIT, Priority.LOW, null, List.of(comment)
        );
        comment.setTask(task);
        CommentDto commentDto = new CommentDto(comment.getId(), comment.getTask().getId(), comment.getText());
        System.out.println("000000000" + comment.getTask().getId());
        when(repository.findByTaskId(task.getId())).thenReturn(List.of(comment));
        List<CommentDto> list = service.findByTaskId(task.getId());
        assertArrayEquals(list.toArray(), List.of(commentDto).toArray());
    }
}