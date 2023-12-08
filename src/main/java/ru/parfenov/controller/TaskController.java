package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/new")
    public ResponseEntity<Void> create(@RequestBody TaskDtoIn task) {
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody TaskDtoIn task) {
        taskService.update(id, task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}