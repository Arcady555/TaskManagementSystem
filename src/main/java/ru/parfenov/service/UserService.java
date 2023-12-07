package ru.parfenov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.parfenov.dto.UserDto;
import ru.parfenov.model.User;
import ru.parfenov.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserDto> findAll() {
        List<User> list = repository.findAll();
        List<UserDto> listDTO = new ArrayList<>();
        for (User user : list) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setCreatedTasks(user.getCreatedTasks().size());
            userDto.setExecuteTasks(user.getExecutedTasks().size());
            listDTO.add(userDto);
        }
        return listDTO;
    }

    public User findById(int id) {
        Optional<User> rsl = repository.findById(id);
        return rsl.orElseGet(() -> new User(
                -1,
                "Пользователь не найден!",
                "",
                "",
                List.of(),
                List.of()
            )
        );
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}