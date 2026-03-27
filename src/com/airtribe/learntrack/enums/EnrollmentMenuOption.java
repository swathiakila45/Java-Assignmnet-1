package com.airtribe.learntrack.enums;

public enum EnrollmentMenuOption {
    ENROLL(1),
    LIST_ALL(2),
    GET_BY_ID(3),
    GET_BY_STUDENT(4),
    GET_BY_COURSE(5),
    UPDATE_STATUS(6),
    DELETE(7),
    BACK(0);

    private final int value;

    EnrollmentMenuOption(int value) {
        this.value = value;
    }

    public static EnrollmentMenuOption fromValue(int value) {
        for (EnrollmentMenuOption option : EnrollmentMenuOption.values()) {
            if (option.value == value) return option;
        }
        return null;
    }
}