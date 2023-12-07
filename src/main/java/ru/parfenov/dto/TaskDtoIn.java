package ru.parfenov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDtoIn {
    private int id;
    private int authorId;
    private String description;
    private int statusId;
    private int priorityId;
    private int executorId;
}
