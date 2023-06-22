package com.thv.tapexam.exception;

public enum ErrorCode {
    UNKNOWN(-1, "Something went wrong. Please try again"),
    NO_ERROR(0, "No error occurred"),
    NOT_FOUND(1, "Data not found"),

    //Subject error code 1XXXX
    SUBJECT_EXISTED(10001, "Subject existed"),
    SUBJECT_NOT_FOUND(10002, "Subject not found"),
    SUBJECT_NAME_EXISTED_OTHER(10003, "Name existed in other subject"),
    SUBJECT_NOT_DATA(10004, "No data subject"),

    //Condition error code 2XXXX
    CONDITION_EXISTED(20001, "Condition existed"),
    CONDITION_NOT_FOUND(20002, "Condition not found"),
    CONDITION_CODE_EXISTED_OTHER(20003, "Code existed in other condition"),
    CONDITION_NOT_DATA(20004, "No data condition"),

    //Other code 3XXXX
    FILE_EMPTY(30001, "File empty"),

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
