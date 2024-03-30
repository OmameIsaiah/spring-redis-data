package com.redis.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {
    private String status;
    private String message;
    private Integer code;
    private Object data;

    public ApiResponse(String status, String message, Integer code, Object data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
