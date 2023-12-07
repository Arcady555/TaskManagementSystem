package ru.parfenov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.parfenov.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
}
