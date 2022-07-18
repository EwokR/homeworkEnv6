package com.example.homeworkenv6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectNameOrSecondNameException extends RuntimeException {
    public IncorrectNameOrSecondNameException() {
        super();
    }

    public IncorrectNameOrSecondNameException(String message) {
        super(message);
    }

    public IncorrectNameOrSecondNameException(String message, Throwable t) {
        super(message,t);
    }

    public IncorrectNameOrSecondNameException(Throwable t) {
        super(t);
    }
}
