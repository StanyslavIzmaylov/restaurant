package com.example.restaurant.service;

import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.user.DataJpaUserRepository;
import com.example.restaurant.util.AuthorizedUser;
import com.example.restaurant.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

import static com.example.restaurant.util.UserUtil.prepareToSave;
import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final DataJpaUserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(DataJpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user, int id) {
        Assert.notNull(user, "menu must not be null");
        checkNotFoundWithId(user, id);
        prepareAndSave(user);
    }
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        Assert.notNull(user, "menu must not be null");
        UserUtil.roleCheck(user);
        return prepareAndSave(user);
    }
    @Cacheable("users")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }
}
