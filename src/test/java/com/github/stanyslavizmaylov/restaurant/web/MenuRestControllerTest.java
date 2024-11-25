package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.data.MenuDataTest;
import com.github.stanyslavizmaylov.restaurant.model.Menu;
import com.github.stanyslavizmaylov.restaurant.service.MenuService;
import com.github.stanyslavizmaylov.restaurant.util.exeption.NotFoundException;
import com.github.stanyslavizmaylov.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.stanyslavizmaylov.restaurant.TestUtil.userHttpBasic;
import static com.github.stanyslavizmaylov.restaurant.data.MenuDataTest.*;
import static com.github.stanyslavizmaylov.restaurant.data.RestaurantDataTest.*;
import static com.github.stanyslavizmaylov.restaurant.data.UserDataTest.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuService menuService;
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';
    private static final String REST_URL_MENU = "/menu/";
    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAUR_ID + REST_URL_MENU + MENU_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAUR_ID + REST_URL_MENU + MENU_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID,RESTAUR_ID));
    }

    @Test
    void save() throws Exception {
        Menu newMenu = MenuDataTest.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAUR_ID + "/menu")
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andDo(print())
                .andExpect(status().isCreated());

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.getId();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId,RESTAUR_ID), newMenu);
    }

    @Test
    void update() throws Exception {
        Menu menuUpdate = MenuDataTest.getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAUR_ID + "/menu/"+ MENU_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(menuUpdate)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(menuService.get(MENU_ID,RESTAUR_ID), menuUpdate);
    }
}
