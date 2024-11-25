package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.data.RestaurantDataTest;
import com.github.stanyslavizmaylov.restaurant.model.Restaurant;
import com.github.stanyslavizmaylov.restaurant.service.RestaurantService;
import com.github.stanyslavizmaylov.restaurant.util.exeption.NotFoundException;
import com.github.stanyslavizmaylov.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.stanyslavizmaylov.restaurant.TestUtil.userHttpBasic;
import static com.github.stanyslavizmaylov.restaurant.data.RestaurantDataTest.*;
import static com.github.stanyslavizmaylov.restaurant.data.UserDataTest.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService restaurantService;
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAUR_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAUR_ID));
    }

    @Test
    void save() throws Exception {
        Restaurant newRestaurant = RestaurantDataTest.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(RestaurantRestController.REST_URL)
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andDo(print())
                .andExpect(status().isCreated());

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant restaurantUpdate = RestaurantDataTest.getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAUR_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(restaurantUpdate)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAUR_ID), restaurantUpdate);
    }
}
