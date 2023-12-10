package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.PersonDto;
import ru.parfenov.model.Person;
import ru.parfenov.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<PersonDto> findAll() {
        List<Person> list = repository.findAll();
        List<PersonDto> listDTO = new ArrayList<>();
        for (Person person : list) {
            PersonDto personDto = new PersonDto();
            personDto.setId(person.getId());
            personDto.setName(person.getName());
            personDto.setCreatedTasks(person.getCreatedTasks().size());
            personDto.setExecuteTasks(person.getExecutedTasks().size());
            listDTO.add(personDto);
        }
        return listDTO;
    }

    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void delete(Person person) {
        repository.delete(person);
    }
}