package com.buythebook.bookstore.userservice.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String mail;
}
