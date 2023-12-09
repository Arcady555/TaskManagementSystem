package ru.parfenov.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ru.parfenov.model.Person;
import ru.parfenov.repository.PersonRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final PersonRepository persons;
    private BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        persons.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return persons.findAll();
    }
}