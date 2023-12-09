package ru.parfenov.model;

import lombok.*;
import ru.parfenov.enums.Priority;
import ru.parfenov.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;
    private String description;
    @Column(name = "status_id")
    private Status status;
    @Column(name = "priority_id")
    private Priority priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private Person executor;
    @OneToMany(mappedBy = "task")
    private List<Comment> comments = new ArrayList<>();
}