package ru.parfenov.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    IN_WAIT(0, "В ожидании"),
    IN_PROGRESS(1, "В процессе"),
    COMPLETED(2, "Завершено");

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
