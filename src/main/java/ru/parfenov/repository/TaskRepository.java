package ru.parfenov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.parfenov.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Override
    List<Task> findAll();

    List<Task> findByAuthorId(int id);

    List<Task> findByExecutorId(int id);
}