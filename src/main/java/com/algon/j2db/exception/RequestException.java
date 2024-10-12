package com.algon.j2db.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestException extends RuntimeException {

    UUID requestId;

    public RequestException(UUID requestId, String message) {
        super(message);
        this.requestId = requestId;
    }
}
