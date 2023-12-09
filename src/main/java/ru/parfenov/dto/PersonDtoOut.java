package ru.parfenov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDtoOut {
    private int id;
    private String name;
    private int createdTasks;
    private int executeTasks;
}
