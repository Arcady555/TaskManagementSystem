package ru.parfenov.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Priority {
    LOW(0, "Низкий"),
    MIDDLE(1, "Средний"),
    HIGH(2, "Высокий");

    private final int id;
    private final String info;

 //   public int getId() {
       // return id;
 //   }

    public String getInfo() {
        return info;
    }

    public static Priority findById(int priorityId) {
        Priority rsl = null;
        for (Priority priority : Priority.values()) {
            if (priority.id == priorityId) {
                rsl = priority;
            }
        }
        return rsl;
    }
}