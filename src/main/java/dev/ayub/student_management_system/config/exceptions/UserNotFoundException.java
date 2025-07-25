package dev.ayub.student_management_system.config.exceptions;

public class UserNotFoundException extends RuntimeException{

    private int errorCode;

    public UserNotFoundException (String message) {
        super(message);
    }

    public UserNotFoundException(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }


}
