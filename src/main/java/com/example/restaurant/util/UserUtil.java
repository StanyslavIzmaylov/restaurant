package com.example.restaurant.util;

import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class UserUtil {

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static User roleCheck(User user) {
        if (user.getRole() == null) {
            user.setRole(Collections.singleton(Role.USER));
        }
        return user;
    }
}
