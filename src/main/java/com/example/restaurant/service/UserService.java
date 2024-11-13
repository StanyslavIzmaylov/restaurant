package com.example.restaurant.service;

import com.example.restaurant.model.User;
import com.example.restaurant.repository.CrudUserRepository;
import com.example.restaurant.util.AuthorizedUser;
import com.example.restaurant.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.example.restaurant.util.UserUtil.prepareToSave;
import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

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
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
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
