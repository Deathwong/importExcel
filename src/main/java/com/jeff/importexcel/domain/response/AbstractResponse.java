package com.jeff.importexcel.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class AbstractResponse {
    private Integer status;
    private Instant date;
    private String message;
}
