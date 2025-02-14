package com.swarnava.ghostrider.config.jwttoken.repo;

import com.swarnava.ghostrider.config.jwttoken.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User findUserByEmail(String email){
        User user = new User(email,"123456");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        return user;
    }
}