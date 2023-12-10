package ru.parfenov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.parfenov.dto.PersonDto;
import ru.parfenov.model.Person;
import ru.parfenov.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceTest {

    @MockBean
    private PersonRepository persons;

    private PersonService service;
    private Person person;


    @BeforeEach
    public void initService() {
        service = new PersonService(persons);
        person = new Person(
                1, "Vasya", "Vasya@mail.ru", "1234", List.of(), List.of()
        );
    }

    @Test
    void findAll() {
        when(persons.findAll()).thenReturn(List.of(person));
        PersonDto personDto = new PersonDto(
                person.getId(), person.getName(), person.getCreatedTasks().size(), person.getExecutedTasks().size()
        );
        List<PersonDto> list = service.findAll();
        assertArrayEquals(list.toArray(), List.of(personDto).toArray());
    }

    @Test
    void findById() {
        when(persons.findById(person.getId())).thenReturn(Optional.of(person));
        Optional<Person> rsl = service.findById(person.getId());
        assertEquals(rsl.get(), person);
    }

    @Test
    void findByEmail() {
        when(persons.findByEmail(person.getEmail())).thenReturn(Optional.of(person));
        Optional<Person> rsl = service.findByEmail(person.getEmail());
        assertEquals(rsl.get(), person);
    }
}