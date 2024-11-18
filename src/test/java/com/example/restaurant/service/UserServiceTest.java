package com.example.restaurant.service;

import com.example.restaurant.data.UserDataTest;
import com.example.restaurant.model.User;
import com.example.restaurant.service.UserService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static com.example.restaurant.data.UserDataTest.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestPropertySource(properties = {"appconfig.enablecache=true"})
@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void delete() {
        userService.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    public void get() {
        User user = userService.get(ADMIN_ID);
        USER_MATCHER.assertMatch(admin, user);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(USER_NOT_FOUND));
    }

    @Test
    public void getByEmail(){
        User user = userService.getByEmail("user@ya.ru");
        USER_MATCHER.assertMatch(UserDataTest.user,user);
    }

    @Test
    public void update() {
        User user = UserDataTest.getUpdate();
        userService.update(user,USER_ID);
        USER_MATCHER.assertMatch(userService.get(USER_ID), user);
    }

    @Test
    public void save() {
        User created = userService.save(UserDataTest.getNew());
        int newId = created.getId();
        User newUser = UserDataTest.getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);

    }
}
