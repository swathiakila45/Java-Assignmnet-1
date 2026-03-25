package src.com.airtribe.learntrack.enums;

public enum StudentMenuOption {
    ADD(1),
    LIST(2),
    GET_BY_ID(3),
    UPDATE(4),
    DELETE(5),
    BACK(0);

    private final int value;

    StudentMenuOption(int value) {
        this.value = value;
    }

    public static StudentMenuOption fromValue(int value) {
        for (StudentMenuOption option : StudentMenuOption.values()) {
            if (option.value == value)
                return option;
        }
        return null;
    }
}
