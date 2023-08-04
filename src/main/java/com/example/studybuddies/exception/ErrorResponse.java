package com.example.studybuddies.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private int status;

    private String message;

    private List<String> errors;

    private String path;

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.message = message;
        this.path = path;
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(HttpStatus status, List<String> errors, String path) {
        this.status = status.value();
        this.errors = errors;
        this.path = path;
        this.message = "";
    }

    public ErrorResponse(HttpStatus status, String message, List<String> errors, String path) {
        this.status = status.value();
        this.message = message;
        this.errors = errors;
        this.path = path;
    }

}
