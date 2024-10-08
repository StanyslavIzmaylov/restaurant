package com.example.restaurant.repository.user;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();
}
