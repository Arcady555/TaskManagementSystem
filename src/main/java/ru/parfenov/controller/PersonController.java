package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.parfenov.dto.PersonDtoOut;
import ru.parfenov.service.PersonService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/all")
    public List<PersonDtoOut> getAllUsers() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDtoOut> getPerson(@PathVariable("id") int id) {
        personService.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}