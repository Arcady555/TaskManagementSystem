package ru.parfenov.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Priority {
    NOT(0, "Нет"),
    LOW(1, "Низкий"),
    MIDDLE(2, "Средний"),
    HIGH(3, "Высокий");

    private final int id;
    private final String info;

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