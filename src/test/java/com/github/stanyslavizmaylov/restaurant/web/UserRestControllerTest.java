package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.model.User;
import com.github.stanyslavizmaylov.restaurant.service.UserService;
import com.github.stanyslavizmaylov.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.stanyslavizmaylov.restaurant.TestUtil.userHttpBasic;
import static com.github.stanyslavizmaylov.restaurant.data.UserDataTest.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserRestControllerTest extends AbstractControllerTest{

    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(UserRestController.REST_URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));
    }
    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(UserRestController.REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete("/rest/admin/users/" + USER_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    void register() throws Exception {
        User newUser = new User(null, "newName", "password", "newemail@ya.ru");
        ResultActions action = perform(MockMvcRequestBuilders.post(UserRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(newUser, newUser.getPassword())))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = USER_MATCHER.readFromJson(action);
        int newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void update() throws Exception {
        User userUpdate = getUpdate();
        perform(MockMvcRequestBuilders.put(UserRestController.REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(jsonWithPassword(userUpdate, userUpdate.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userService.get(USER_ID), userUpdate);
    }
}
