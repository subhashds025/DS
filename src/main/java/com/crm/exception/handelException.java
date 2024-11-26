package com.crm.exception;

import com.crm.payLoad.DetailsError;
import com.crm.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class handelException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<DetailsError> handelException(ResourceNotFound e, WebRequest request){
        DetailsError de=new DetailsError(e.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(de, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
