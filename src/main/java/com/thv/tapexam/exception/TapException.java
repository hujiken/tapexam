package com.thv.tapexam.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class TapException extends Exception {
    private Integer errorCode;
    private Optional data;

    public TapException(String message) {
        super(message);
    }

    public TapException(String message, Integer errorCode) {
        this(message);
        this.errorCode = errorCode;
        this.data = Optional.empty();
    }

    public TapException(ErrorCode error) {
        this(error.getMessage(), error.getCode());
    }

    public TapException(ErrorCode error, Optional data) {
        this(error);
        this.data = data;
    }
}
