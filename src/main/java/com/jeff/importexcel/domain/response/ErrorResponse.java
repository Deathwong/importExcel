package com.jeff.importexcel.domain.response;

import java.time.Instant;

public class ErrorResponse extends AbstractResponse {
    public ErrorResponse(Integer status, String message) {
        super(status, Instant.now(), message);
    }
}
