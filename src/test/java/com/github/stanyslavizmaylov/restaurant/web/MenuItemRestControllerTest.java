package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.data.MenuItemDataTest;
import com.github.stanyslavizmaylov.restaurant.model.MenuItem;
import com.github.stanyslavizmaylov.restaurant.service.MenuItemService;
import com.github.stanyslavizmaylov.restaurant.util.exeption.NotFoundException;
import com.github.stanyslavizmaylov.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.stanyslavizmaylov.restaurant.TestUtil.userHttpBasic;
import static com.github.stanyslavizmaylov.restaurant.data.MenuItemDataTest.*;
import static com.github.stanyslavizmaylov.restaurant.data.MenuDataTest.*;
import static com.github.stanyslavizmaylov.restaurant.data.UserDataTest.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuItemRestControllerTest extends AbstractControllerTest{

    @Autowired
    private MenuItemService menuItemService;

    private static final String REST_URL = MenuItemRestController.REST_URL + '/';
    private static final String REST_URL_MEAL = "/menu-item/";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_ID + REST_URL_MEAL + MENU_ITEM_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(menuItem));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU_ID + REST_URL_MEAL + MENU_ITEM_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> menuItemService.get(MENU_ITEM_ID,MENU_ID));
    }

    @Test
    void save() throws Exception {
        MenuItem newMenuItem = MenuItemDataTest.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post( REST_URL + MENU_ID + "/menu-item")
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuItem)))
                .andDo(print())
                .andExpect(status().isCreated());

        MenuItem created = MENU_ITEM_MATCHER.readFromJson(action);
        int newId = created.getId();
        newMenuItem.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newMenuItem);
        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(newId,MENU_ID), newMenuItem);
    }

    @Test
    void update() throws Exception {
        MenuItem menuItemUpdate = MenuItemDataTest.getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + MENU_ID + "/menu-item/" + MENU_ITEM_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(menuItemUpdate)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(MENU_ITEM_ID,MENU_ID), menuItemUpdate);
    }
}
