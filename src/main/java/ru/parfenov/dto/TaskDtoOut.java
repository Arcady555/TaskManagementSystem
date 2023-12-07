package ru.parfenov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDtoOut {
    private int id;
    private String author;
    private String description;
    private String status;
    private String priority;
    private String executor;
    private int commentAmount;
}
