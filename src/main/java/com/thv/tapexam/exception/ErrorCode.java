package com.thv.tapexam.exception;

public enum ErrorCode {
    UNKNOWN(-1, "Something went wrong. Please try again"),
    NO_ERROR(0, "No error occurred"),
    NOT_FOUND(1, "Data not found"),

    //Division error code 1XXXX
    DIVISION_EXISTED(10001, "Division name or code existed"),
    DIVISION_NOT_FOUND(10002, "Division not found"),
    DIVISION_NAME_EXISTED_OTHER(10003, "Name existed in other division"),

    //Subject error code 2XXXX
    SUBJECT_EXISTED(20001, "Subject existed"),
    SUBJECT_NOT_FOUND(20002, "Subject not found"),
    SUBJECT_NAME_EXISTED_OTHER(20003, "Name existed in other subject"),

    //Condition error code 3XXXX
    CONDITION_EXISTED(30001, "Condition existed"),
    CONDITION_NOT_FOUND(30002, "Condition not found"),
    CONDITION_CODE_EXISTED_OTHER(20003, "Code existed in other condition"),

    BAD_REQUEST(2, "Bad request");

    private final Integer value;
    private final String reasonPhrase;

    ErrorCode(Integer value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public Integer getCode() {
        return this.value;
    }

    public String getMessage() {
        return this.reasonPhrase;
    }
}
