package ru.parfenov.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    NOT(0, "Нет"),
    IN_WAIT(1, "В ожидании"),
    IN_PROGRESS(2, "В процессе"),
    COMPLETED(3, "Завершено");

    private final int id;
    private final String info;



    public int getId(int id) {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public static Status findById(int statusId) {
        Status rsl = null;
        for (Status status : Status.values()) {
            if (status.id == statusId) {
                rsl = status;
            }
        }
        return rsl;
    }
}