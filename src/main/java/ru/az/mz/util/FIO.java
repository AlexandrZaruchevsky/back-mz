package ru.az.mz.util;

public enum FIO {

    LAST_NAME("lastName"),
    FIRST_NAME("firstName"),
    MIDDLE_NAME("middleName");

    private final String fio;

    FIO(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

}
