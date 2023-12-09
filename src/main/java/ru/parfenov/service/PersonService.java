package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.PersonDtoIn;
import ru.parfenov.dto.PersonDtoOut;
import ru.parfenov.model.Person;
import ru.parfenov.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<PersonDtoOut> findAll() {
        List<Person> list = repository.findAll();
        List<PersonDtoOut> listDTO = new ArrayList<>();
        for (Person person : list) {
            PersonDtoOut personDtoOut = new PersonDtoOut();
            personDtoOut.setId(person.getId());
            personDtoOut.setName(person.getName());
            personDtoOut.setCreatedTasks(person.getCreatedTasks().size());
            personDtoOut.setExecuteTasks(person.getExecutedTasks().size());
            listDTO.add(personDtoOut);
        }
        return listDTO;
    }

    public Person findById(int id) {
        Optional<Person> rsl = repository.findById(id);
        return rsl.orElseGet(() -> findById(1));
    }

   /* public Person create(PersonDtoIn personDtoIn) {
        Person person = new Person();
        person.setName(personDtoIn.getName());
        person.setEmail(personDtoIn.getEmail());
        person.setPassword(personDtoIn.getPassword());
        return repository.save(person);
    } */

    public void delete(Person person) {
        repository.delete(person);
    }
}