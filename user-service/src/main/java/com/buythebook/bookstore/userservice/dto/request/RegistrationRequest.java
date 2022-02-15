package com.buythebook.bookstore.userservice.dto.request;

import com.buythebook.bookstore.userservice.model.UserType;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {
    @NotBlank
    @Size(max = 20, message = "Username should be less than 20 characters.")
    private String userName;

    @NotBlank
    @Size(max = 30, message = "First name should be less than 30 characters.")
    private String firstName;

    @NotBlank
    @Size(max = 30, message = "Last name should be less than 30 characters.")
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 30, message = "Mail should be less than 30 characters.")
    private String mail;

    @NotBlank
    @Size(min = 6, message = "Password should have at least 6 characters.")
    private String password;

    @NotBlank
    private String userType;
}
