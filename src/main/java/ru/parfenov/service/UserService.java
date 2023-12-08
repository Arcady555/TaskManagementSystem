package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.UserDtoIn;
import ru.parfenov.dto.UserDtoOut;
import ru.parfenov.model.User;
import ru.parfenov.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserDtoOut> findAll() {
        List<User> list = repository.findAll();
        List<UserDtoOut> listDTO = new ArrayList<>();
        for (User user : list) {
            UserDtoOut userDtoOut = new UserDtoOut();
            userDtoOut.setId(user.getId());
            userDtoOut.setName(user.getName());
            userDtoOut.setCreatedTasks(user.getCreatedTasks().size());
            userDtoOut.setExecuteTasks(user.getExecutedTasks().size());
            listDTO.add(userDtoOut);
        }
        return listDTO;
    }

    public User findById(int id) {
        Optional<User> rsl = repository.findById(id);
        return rsl.orElseGet(() -> findById(1));
    }

    public User create(UserDtoIn userDtoIn) {
        User user = new User();
        user.setName(userDtoIn.getName());
        user.setEmail(userDtoIn.getEmail());
        user.setPassword(userDtoIn.getPassword());
        return repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}