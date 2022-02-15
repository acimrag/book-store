package com.buythebook.bookstore.userservice.repository;


import com.buythebook.bookstore.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Boolean existsByUserName(String userName);
    Boolean existsByMail(String mail);
    Optional<User> findByUserName(String userName);
}
