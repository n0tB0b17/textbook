package com.alpha.textbook.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {
    private LocalDateTime timeStamp;
    private String message;
    private String path;
    private int status;
}
