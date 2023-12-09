package ru.parfenov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.parfenov.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    @Override
    List<Person> findAll();

    Optional<Person> findByEmail(String email);
}
