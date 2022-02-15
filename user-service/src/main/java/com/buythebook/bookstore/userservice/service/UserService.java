package com.buythebook.bookstore.userservice.service;

import com.buythebook.bookstore.userservice.dto.request.RegistrationRequest;
import com.buythebook.bookstore.userservice.dto.response.UserResponse;
import com.buythebook.bookstore.userservice.model.User;
import com.buythebook.bookstore.userservice.model.UserType;
import com.buythebook.bookstore.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void createUser(RegistrationRequest registrationRequest) {
        checkFields(registrationRequest);
        User user = User.builder()
                .userName(registrationRequest.getUserName())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .mail(registrationRequest.getMail())
                .userType(UserType.valueOf(registrationRequest.getUserType().toUpperCase()))
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
        userRepository.save(user);
    }

    public UserResponse getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("UserName doesn't exist:" + userName));
        return UserResponse.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .build();
    }

    private void checkFields(RegistrationRequest registrationRequest) {
        String errorMessage = null;
        if (!Arrays.stream(UserType.values()).anyMatch(e -> e.name().equals(registrationRequest.getUserType().toUpperCase()))) {
            errorMessage = "Not a valid user type.";
        } else if(userRepository.existsByUserName(registrationRequest.getUserName())) {
            errorMessage = "User is taken. Try another one";
        } else if(userRepository.existsByMail(registrationRequest.getMail())) {
            errorMessage = "Mail address is taken. Try another one";
        }

        if(errorMessage != null) {
            throw new RuntimeException(errorMessage);
        }
    }
}
