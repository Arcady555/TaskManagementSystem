package ru.parfenov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private String description;
    @Column(name = "status_id")
    private Status status;
    @Column(name = "priority_id")
    private Priority priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private User executor;
    @OneToMany(mappedBy = "task")
    private List<Comment> comments = new ArrayList<>();
}