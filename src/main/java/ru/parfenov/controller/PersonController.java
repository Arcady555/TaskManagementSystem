package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.parfenov.dto.PersonDto;
import ru.parfenov.service.PersonService;
import ru.parfenov.utility.Utility;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/all")
    public List<PersonDto> getAllUsers() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") int id) {
        var person = personService.findById(id);
        return person.map(value -> new ResponseEntity<>(
                Utility.getPersonDtoFromPerson(value),
                HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}