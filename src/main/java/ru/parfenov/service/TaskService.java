package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.TaskDtoIn;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;
import ru.parfenov.model.Person;
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

    public Optional<Task> findById(int taskId) {
        return repository.findById(taskId);
    }

    public void create(TaskDtoIn taskDtoIn, Authentication authentication) {
        Person executor = new Person();
        if (personService.findByEmail(authentication.getName()).isEmpty()) {
            throw new IllegalArgumentException(Utility.EXCEPTION_AUTHOR_MASSAGE);
        }
        if (taskDtoIn.getExecutorId() == 0) {
            executor.setName("Не назначен");
        } else if (taskDtoIn.getExecutorId() != 0 && personService.findById(taskDtoIn.getExecutorId()).isEmpty()) {
            throw new IllegalArgumentException(Utility.EXCEPTION_EXECUTOR_MASSAGE);
        } else {
            executor = personService.findById(taskDtoIn.getExecutorId()).get();
        }
        Task task = new Task(
                0,
                personService.findByEmail(authentication.getName()).get(),
                taskDtoIn.getDescription(),
                Status.IN_WAIT,
                Priority.findById(taskDtoIn.getPriorityId()),
                executor,
                new ArrayList<>()
        );
        repository.save(task);
    }

    public void update(int taskId, TaskDtoIn taskDtoIn, Authentication authentication) {
        if (taskDtoIn.getExecutorId() != 0 && personService.findById(taskDtoIn.getExecutorId()).isEmpty()) {
            throw new IllegalArgumentException(Utility.EXCEPTION_EXECUTOR_MASSAGE);
        }
        Optional<Task> taskOptional = findById(taskId);
        if (taskOptional.isPresent() && taskDtoIn.getStatusId() != 3) {
            Task task = taskOptional.get();
            if (authentication.getName().equals(task.getAuthor().getEmail())) {
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
                    task.setExecutor(personService.findById(taskDtoIn.getExecutorId()).get());
                }
                repository.save(task);
            }
        } else {
            throw new IllegalArgumentException(Utility.EXCEPTION_TASK_MASSAGE);
        }
    }

    public void updateStatus(int taskId, TaskDtoIn taskDtoIn, Authentication authentication) {
        Optional<Task> taskOptional = findById(taskId);
        if (taskOptional.isPresent() && taskDtoIn.getStatusId() != 3) {
            Task task = taskOptional.get();
            if (authentication.getName().equals(task.getExecutor().getEmail())) {
                if (taskDtoIn.getStatusId() != 0) {
                    task.setStatus(Status.findById(taskDtoIn.getStatusId()));
                }
                repository.save(task);
            }
        } else {
            throw new IllegalArgumentException(Utility.EXCEPTION_TASK_MASSAGE);
        }
    }

    public void delete(int id, Authentication authentication) {
        Optional<Task> taskOptional = findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            if (authentication.getName().equals(task.getAuthor().getEmail())) {
                repository.delete(task);
            }
        }
    }
}