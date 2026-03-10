package com.gsilverio.simpleapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gsilverio.simpleapi.model.dto.response.pagination.PaginationMeta;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private PaginationMeta pagination;
    private List<String> errors;
    private String message;

    private ApiResponse() {}

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> success(T data, PaginationMeta pagination) {
        ApiResponse<T> response = new ApiResponse<>();
        response.data = data;
        response.pagination = pagination;
        return response;
    }

    public static <T> ApiResponse<T> error(List<String> errors, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.errors = errors;
        response.message = message;
        return response;
    }
}
