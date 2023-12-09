package ru.parfenov.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.parfenov.model.Person;
import ru.parfenov.repository.PersonRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository persons;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> optionalUser = persons.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        return new User(optionalUser.get().getEmail(), optionalUser.get().getPassword(), emptyList());
    }
}