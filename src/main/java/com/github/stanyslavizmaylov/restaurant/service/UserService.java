package com.github.stanyslavizmaylov.restaurant.service;

import com.github.stanyslavizmaylov.restaurant.model.User;
import com.github.stanyslavizmaylov.restaurant.repository.CrudUserRepository;
import com.github.stanyslavizmaylov.restaurant.util.AuthorizedUser;
import com.github.stanyslavizmaylov.restaurant.util.UserUtil;
import com.github.stanyslavizmaylov.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final CrudUserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(CrudUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Transactional
    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user, int id) {
        Assert.notNull(user, "menu must not be null");
        ValidationUtil.checkNotFoundWithId(user, id);
        prepareAndSave(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        Assert.notNull(user, "menu must not be null");
        UserUtil.roleCheck(user);
        return prepareAndSave(user);
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
        return userRepository.save(UserUtil.prepareToSave(user, passwordEncoder));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
