package com.example.homeworkenv6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable t) {
        super(message,t);
    }

    public EmployeeNotFoundException(Throwable t) {
        super(t);
    }
}
