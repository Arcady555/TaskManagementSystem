package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.TaskDtoIn;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;
import ru.parfenov.model.Task;
import ru.parfenov.repository.TaskRepository;
import ru.parfenov.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final PersonService personService;

    public List<TaskDtoOut> findAll() {
        List<Task> list = repository.findAll();
        return Utility.getTaskOutsFromTasks(list);
    }

    public List<TaskDtoOut> findAllOfAuthor(int personId) {
        List<Task> list = repository.findByAuthorId(personId);
        return Utility.getTaskOutsFromTasks(list);
    }

    public List<TaskDtoOut> findAllOfExecutor(int personId) {
        List<Task> list = repository.findByExecutorId(personId);
        return Utility.getTaskOutsFromTasks(list);
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
                personService.findById(taskDtoIn.getAuthorId()),
                taskDtoIn.getDescription(),
                Status.IN_WAIT,
                Priority.findById(taskDtoIn.getPriorityId()),
                personService.findById(taskDtoIn.getExecutorId()),
                new ArrayList<>()
        );
        repository.save(task);
    }

    public void update(int taskId, TaskDtoIn taskDtoIn) {
        Task task = findById(taskId);
        if (!"Задание не найдено!".equals(task.getDescription()) && taskDtoIn.getStatusId() != 3) {
            if (taskDtoIn.getDescription() != null) {
                task.setDescription(taskDtoIn.getDescription());
            }
            if (taskDtoIn.getStatusId() != 0) {
                task.setStatus(Status.findById(taskDtoIn.getStatusId()));
            }
            if (taskDtoIn.getPriorityId() != 0) {
                task.setPriority(Priority.findById(taskDtoIn.getPriorityId()));
            }
            if (taskDtoIn.getExecutorId() != 0) {
                task.setExecutor(personService.findById(taskDtoIn.getExecutorId()));
            }
            repository.save(task);
        }
    }

    public void updateStatus(int taskId, TaskDtoIn taskDtoIn) {
        Task task = findById(taskId);
        if (!"Задание не найдено!".equals(task.getDescription()) && taskDtoIn.getStatusId() != 3) {
            if (taskDtoIn.getStatusId() != 0) {
                task.setStatus(Status.findById(taskDtoIn.getStatusId()));
            }
            repository.save(task);
        }
    }

    public void delete(int id) {
        Task task = findById(id);
        if (!"Задание не найдено!".equals(task.getDescription())) {
            repository.delete(task);
        }
    }
}