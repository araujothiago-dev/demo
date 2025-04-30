package com.example.demo.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private Long timestamp;
    private String error;
    private String msg;
    private String path;
}
