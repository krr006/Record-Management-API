package com.krr006.records.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Record with id=" + id + " not found");
    }
}
