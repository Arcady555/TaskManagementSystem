package ru.parfenov.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Task> createdTasks = new ArrayList<>();

    @OneToMany(mappedBy = "executor")
    private List<Task> executedTasks = new ArrayList<>();
}