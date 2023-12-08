package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.parfenov.dto.UserDtoIn;
import ru.parfenov.dto.UserDtoOut;
import ru.parfenov.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public List<UserDtoOut> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody UserDtoIn user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}