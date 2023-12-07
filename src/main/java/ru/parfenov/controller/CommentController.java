package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.parfenov.dto.CommentDto;
import ru.parfenov.service.CommentService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class CommentController {
    private final CommentService service;

    @GetMapping("/{id}/comments")
    public List<CommentDto> findAll(@PathVariable("id") int taskId) {
        return service.findByTaskId(taskId);
    }

    @PostMapping("/{id}/new_comment")
    ResponseEntity<Void> create(@PathVariable("id") int taskId, @RequestBody String text) {
        service.create(taskId, text);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete_comment/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}