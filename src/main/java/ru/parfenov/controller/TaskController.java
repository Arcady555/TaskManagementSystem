package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.parfenov.dto.TaskDtoIn;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.service.TaskService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public List<TaskDtoOut> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/author/{personId}")
    public List<TaskDtoOut> findAllOfAuthor(@PathVariable("personId") int personId) {
        return taskService.findAllOfAuthor(personId);
    }

    @GetMapping("/executor/{personId}")
    public List<TaskDtoOut> findAllOfExecutor(@PathVariable("personId") int personId) {
        return taskService.findAllOfExecutor(personId);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> create(@RequestBody TaskDtoIn task) {
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> update(@PathVariable("id") int id,
                                       @RequestBody TaskDtoIn task,
                                       Authentication authentication) {
        taskService.update(id, task, authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<Void> updateOnlyStatus(@PathVariable("id") int id,
                                                 @RequestBody TaskDtoIn task,
                                                 Authentication authentication) {
        taskService.updateStatus(id, task, authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") int id, Authentication authentication) {
        taskService.delete(id, authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}