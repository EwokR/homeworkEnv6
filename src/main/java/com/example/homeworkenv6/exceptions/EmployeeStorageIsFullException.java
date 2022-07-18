package com.example.homeworkenv6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException() {
        super();
    }

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }

    public EmployeeStorageIsFullException(String message, Throwable t) {
        super(message,t);
    }

    public EmployeeStorageIsFullException(Throwable t) {
        super(t);
    }
}
