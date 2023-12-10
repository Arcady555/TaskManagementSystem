package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ru.parfenov.model.Person;
import ru.parfenov.repository.PersonRepository;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final PersonRepository persons;
    private BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@Valid @RequestBody Person person) {
        if (checkFields(person)) {
            throw new IllegalArgumentException("Некоторые из полей пустые или введены не корректно!");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        return new ResponseEntity<>(this.persons.save(person), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return persons.findAll();
    }

    private boolean checkFields(Person person) {
        return person.getName().length() == 0
                || person.getName().length() > 10
                || person.getEmail().length() == 0
                || !person.getEmail().contains("@")
                || person.getPassword().length() < 4
                || person.getPassword().length() > 10;
    }
}