package com.springmvc.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String timestamp;
    private final int status;
}
