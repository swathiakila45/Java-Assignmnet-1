package com.airtribe.learntrack.enums;

public enum MainMenuOption {
    MANAGE_STUDENTS(1),
    MANAGE_COURSES(2),
    MANAGE_ENROLLMENTS(3),
    EXIT(0);

    private final int value;

    MainMenuOption(int value) {
        this.value = value;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption option : MainMenuOption.values()) {
            if (option.value == value) return option;
        }
        return null;
    }
}