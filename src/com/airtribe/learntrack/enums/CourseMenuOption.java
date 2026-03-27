package com.airtribe.learntrack.enums;

public enum CourseMenuOption {
    ADD(1),
    LIST(2),
    GET_BY_ID(3),
    UPDATE(4),
    DELETE(5),
    BACK(0);

    private final int value;

    CourseMenuOption(int value) {
        this.value = value;
    }

    public static CourseMenuOption fromValue(int value) {
        for (CourseMenuOption option : CourseMenuOption.values()) {
            if (option.value == value) return option;
        }
        return null;
    }
}