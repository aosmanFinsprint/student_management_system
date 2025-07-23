package dev.ayub.student_management_system.model.enums.definaton;


import java.util.Arrays;

// Enum for Status
public enum StatusEnum {
    INACTIVE(0),  // 0 maps to INACTIVE
    ACTIVE(1);    // 1 maps to ACTIVE

    private final int code;

    // Constructor
    StatusEnum(int code) {
        this.code = code;
    }

    // Getter
    public int getCode() {
        return code;
    }

    // Reverse mapping: from Integer to Enum
    public static StatusEnum fromCode(int code) {
        return Arrays.stream(StatusEnum.values())
                .filter(e -> e.getCode() == code)
                .findFirst()


                .orElseThrow(() -> new IllegalArgumentException("Invalid status code: " + code));
    }
}



