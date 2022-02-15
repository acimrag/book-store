package com.buythebook.bookstore.userservice.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
