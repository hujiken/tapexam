package com.thv.tapexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseException {
    public static ResponseEntity<Object> response(Exception e) {
        if (e instanceof TapException) {
            return responseTapException((TapException) e);
        }
        return responseOtherException(e);
    }

    private static ResponseEntity<Object> responseTapException(TapException e) {
        ErrorResponse errorResponse = ErrorResponse.builder().code(e.getErrorCode()).message(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    private static ResponseEntity<Object> responseOtherException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.builder().code(ErrorCode.UNKNOWN.getCode()).message(ErrorCode.UNKNOWN.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
