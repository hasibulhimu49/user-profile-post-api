package com.example.user_profile_post_api.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;


//@ControllerAdvice
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //@ResponseBody
    public String handleRuntimeException(RuntimeException e)
    {
        return e.getMessage();
    }



    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    /*

    //Normal Way
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNotFound(EntityNotFoundException ex)
    {
        Map<String,String> error=new HashMap<>();
        error.put("error","Not Founddddd");
        error.put("message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    //Best Way
    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFound2(EntityNotFoundException ex)
    {
        ProblemDetail problem=ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Resounce Not Found");
        problem.setDetail(ex.getMessage());
        return problem;
    }


     */


}
