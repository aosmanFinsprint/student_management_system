package dev.ayub.student_management_system.config.Utils;

import dev.ayub.student_management_system.model.dto.ErrorResponseDTO;
import dev.ayub.student_management_system.model.dto.SuccessResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ApiResponseUtil {

    public static <T> ResponseEntity<SuccessResponseDTO<T>> success(T data, String message) {
        SuccessResponseDTO<T> response = SuccessResponseDTO.<T>builder()
                .status(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<SuccessResponseDTO<T>> success(T data, String message, HttpStatus status) {
        SuccessResponseDTO<T> response = SuccessResponseDTO.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ErrorResponseDTO> error(HttpStatus status, String message, Map<String, String> errors) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .status(status.value())
                .message(message)
                .errors(errors)
                .build();
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ErrorResponseDTO> error(HttpStatus status, String message) {
        return error(status, message, null);
    }
}