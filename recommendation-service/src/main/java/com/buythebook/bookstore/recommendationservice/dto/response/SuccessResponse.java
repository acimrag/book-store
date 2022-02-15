package com.buythebook.bookstore.recommendationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse {
    private String message;
    private String detail;
}
