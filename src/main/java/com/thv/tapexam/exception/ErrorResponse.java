package com.thv.tapexam.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Integer code;
    private String message;

    public ErrorResponse(Integer code) {
        this.code = code;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
