package com.example.restaurant.service;

import com.example.restaurant.model.User;
import com.example.restaurant.repository.user.DataJpaUserRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final DataJpaUserRepository userRepository;

    public UserService(DataJpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(userRepository.get(id), id);
    }

    public void update(User user) {
        Assert.notNull(user, "menu must not be null");
        userRepository.save(user);
    }

    public User save(User user) {
        Assert.notNull(user, "menu must not be null");
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
