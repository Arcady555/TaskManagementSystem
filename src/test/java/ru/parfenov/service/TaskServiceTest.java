package ru.parfenov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;
import ru.parfenov.model.Person;
import ru.parfenov.model.Task;
import ru.parfenov.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private PersonService personService;

    private TaskService service;
    private Task task1;
    private Task task2;
    private TaskDtoOut taskDtoOut1;
    private TaskDtoOut taskDtoOut2;


    @BeforeEach
    public void initService() {
        service = new TaskService(taskRepository, personService);
        Person author = new Person(
                1, "Author", "Author@mail.ru", "1234", List.of(), List.of()
        );
        Person executor = new Person(
                2, "Executor", "Executor@mail.ru", "1234", List.of(), List.of()
        );
        task1 = new Task(1, author, "Task", Status.IN_WAIT, Priority.LOW, executor, List.of());
        task2 = new Task(2, executor, "Task", Status.IN_WAIT, Priority.LOW, author, List.of());
        taskDtoOut1 = new TaskDtoOut(
                task1.getId(), task1.getAuthor().getName(), task1.getDescription(), task1.getStatus().getInfo(),
                task1.getPriority().getInfo(), task1.getExecutor().getName(), task1.getComments().size()
        );
        taskDtoOut2 = new TaskDtoOut(
                task2.getId(), task2.getAuthor().getName(), task2.getDescription(), task2.getStatus().getInfo(),
                task2.getPriority().getInfo(), task2.getExecutor().getName(), task2.getComments().size()
        );
    }

    @Test
    void findAll() {
        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));
        List<TaskDtoOut> list = service.findAll();
        assertArrayEquals(list.toArray(), List.of(taskDtoOut1, taskDtoOut2).toArray());
    }

    @Test
    void findAllOfAuthor() {
        when(taskRepository.findByAuthorId(task1.getAuthor().getId())).thenReturn(List.of(task1));
        List<TaskDtoOut> list = service.findAllOfAuthor(task1.getAuthor().getId());
        assertArrayEquals(list.toArray(), List.of(taskDtoOut1).toArray());
    }

    @Test
    void findAllOfExecutor() {
        when(taskRepository.findByExecutorId(task1.getExecutor().getId())).thenReturn(List.of(task1));
        List<TaskDtoOut> list = service.findAllOfExecutor(task1.getExecutor().getId());
        assertArrayEquals(list.toArray(), List.of(taskDtoOut1).toArray());
    }

    @Test
    void findById() {
        when(taskRepository.findById(task1.getId())).thenReturn(Optional.of(task1));
        Optional<Task> rsl = service.findById(task1.getId());
        assertEquals(rsl.get(), task1);
    }
}