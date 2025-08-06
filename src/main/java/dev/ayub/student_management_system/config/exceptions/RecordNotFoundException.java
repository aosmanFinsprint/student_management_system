package dev.ayub.student_management_system.config.exceptions;

public class RecordNotFoundException  extends RuntimeException{

    private int errorCode;

    public RecordNotFoundException(String message){
        super(message);
    }

    public RecordNotFoundException(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public RecordNotFoundException(Throwable cause, int errorCode){
        super(cause);
        this.errorCode=errorCode;
    }
}
