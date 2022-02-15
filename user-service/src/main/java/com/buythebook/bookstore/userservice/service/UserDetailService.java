package com.buythebook.bookstore.userservice.service;

import com.buythebook.bookstore.userservice.model.User;
import com.buythebook.bookstore.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("UserName doesn't exist:" + userName));
        return new org.springframework.security.core.userdetails.
                User(user.getUserName(),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority(user.getUserType().toString())}));
    }
}
