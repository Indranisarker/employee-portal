package com.example.HRportal.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorObject {
    private String message;
    private String statusCode;
   // private int status;
    private LocalDateTime dateTime;
}
