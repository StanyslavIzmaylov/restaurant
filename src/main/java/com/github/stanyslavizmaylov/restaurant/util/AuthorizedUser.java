package com.github.stanyslavizmaylov.restaurant.util;

import com.github.stanyslavizmaylov.restaurant.model.User;

import java.io.Serial;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRole());
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }
}
