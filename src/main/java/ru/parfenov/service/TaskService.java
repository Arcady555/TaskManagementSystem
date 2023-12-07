package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.TaskDtoIn;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;
import ru.parfenov.model.Task;
import ru.parfenov.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserService userService;

    public List<TaskDtoOut> findAll() {
        List<Task> list = repository.findAll();
        List<TaskDtoOut> listDTO = new ArrayList<>();
        for (Task task : list) {
            TaskDtoOut taskDTO = new TaskDtoOut();
            taskDTO.setId(task.getId());
            taskDTO.setAuthor(task.getAuthor().getName());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setStatus(task.getStatus().getInfo());
            taskDTO.setPriority(task.getPriority().getInfo());
            taskDTO.setExecutor(task.getExecutor().getName());
            taskDTO.setCommentAmount(task.getComments().size());
            listDTO.add(taskDTO);
        }
        return listDTO;
    }

    public Task findById(int taskId) {
        Optional<Task> rsl = repository.findById(taskId);
        return rsl.orElseGet(() -> new Task(-1,
                null,
                "Задание не найдено!",
                null,
                null,
                null,
                List.of()
            )
        );
    }

    public void create(TaskDtoIn taskDtoIn) {
        Task task = new Task(
                0,
                userService.findById(taskDtoIn.getAuthorId()),
                taskDtoIn.getDescription(),
                Status.IN_WAIT,
                Priority.findById(taskDtoIn.getPriorityId()),
                userService.findById(taskDtoIn.getExecutorId()),
                new ArrayList<>()
                );
        repository.save(task);
    }

    public Task update(int taskId, TaskDtoIn taskDtoIn) {
        Task task = findById(taskId);
        if (!"Задание не найдено!".equals(task.getDescription())) {
            task.setDescription(taskDtoIn.getDescription());
            task.setStatus(Status.findById(taskDtoIn.getStatusId()));
            task.setPriority(Priority.findById(taskDtoIn.getPriorityId()));
            task.setExecutor(userService.findById(taskDtoIn.getExecutorId()));
            return repository.save(task);
        } else {
            return null;
        }
    }

    public void delete(int id) {
        Task task = findById(id);
        if (!"Задание не найдено!".equals(task.getDescription())) {
            repository.delete(task);
        }
    }
}