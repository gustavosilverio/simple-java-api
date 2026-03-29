package com.gsilverio.simpleapi.config;

import tools.jackson.core.JacksonException;
import com.gsilverio.simpleapi.domain.dto.config.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import tools.jackson.databind.ObjectMapper;

@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class convertertype){
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response){
        if (body instanceof ApiResponse)
            return body;

        if (body instanceof Page<?> page)
            return ApiResponse.success(page);

        if (body instanceof String){
            try {
                return objectMapper.writeValueAsString(ApiResponse.success(body));
            } catch (JacksonException e){
                throw new RuntimeException("error while serializing response", e);
            }
        }

        return ApiResponse.success(body);
    }
}
