package com.buythebook.bookstore.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestErrorResponse {
    private String errorCode;
    private String errorMessage;
}
