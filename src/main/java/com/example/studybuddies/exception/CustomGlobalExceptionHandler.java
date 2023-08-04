package com.example.studybuddies.exception;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse processValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {

        final List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        log.error(errors.stream().collect(Collectors.joining("\n")), ex.getCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, errors.get(0), errors, request.getRequestURI());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse processValidationError(MissingServletRequestParameterException ex,
                                                   HttpServletRequest request) {
        log.error(ex.getMessage(), ex.getRootCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(UniqueResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse processValidationError(UniqueResourceException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(value = UserLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleUserLoginException(UserLoginException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(value = InvalidTokenRequestException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleInvalidTokenException(InvalidTokenRequestException ex, HttpServletRequest request) {
        log.debug(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(value = PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePropertyNotExistException(PropertyReferenceException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ErrorResponse handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Arrays.asList(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ErrorResponse handleTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                           HttpServletRequest request) {
        String varName = ex.getName();
        String reqType = ex.getRequiredType().getSimpleName();
        String actValue = ex.getValue().toString();
        String msg = "Type mismatch! " + varName + "[\"" + actValue + "\"] must be " + reqType;
        log.error(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, msg, Arrays.asList(msg), request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                     HttpServletRequest request) {
        String message = ex.getMessage().split(":")[0];
        log.error(ex.getMessage(), ex.getCause());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, message, Arrays.asList(message), request.getRequestURI());
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public final ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex,
                                                                             HttpServletRequest request) {
        String message = ex.getReason();
        ErrorResponse respBody = new ErrorResponse((HttpStatus) ex.getStatusCode(), message, Arrays.asList(message),
                request.getRequestURI());
        return new ResponseEntity<ErrorResponse>(respBody, (HttpStatus) ex.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public final ErrorResponse handleAllOtherExceptions(RuntimeException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        if (message == null)
            message = ex.getCause().getMessage();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, Arrays.asList(message),
                request.getRequestURI());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public final ErrorResponse handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request) {
        String message = "Error 406! " + exc.getMessage();
        return new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, message, request.getRequestURI());
    }
}
