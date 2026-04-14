package com.example.Normad_stay_real_backend.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse<T> {

    private String statusCode;
    private boolean success;
    private String message;
    private T data;
    private String error;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .statusCode("200")
                .success(true)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode("200")
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String error) {
        return ApiResponse.<T>builder()
                .statusCode("400")
                .success(false)
                .error(error)
                .build();
    }

    public static <T> ApiResponse<T> error(String message, String error) {
        return ApiResponse.<T>builder()
                .statusCode("400")
                .success(false)
                .message(message)
                .error(error)
                .build();
    }
}