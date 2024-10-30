package com.example.restaurant.web;

import com.example.restaurant.data.MealDataTest;
import com.example.restaurant.data.MenuDataTest;
import com.example.restaurant.model.Meal;
import com.example.restaurant.model.Menu;
import com.example.restaurant.service.MealService;
import com.example.restaurant.util.exeption.NotFoundException;
import com.example.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurant.TestUtil.userHttpBasic;
import static com.example.restaurant.data.MealDataTest.*;
import static com.example.restaurant.data.MenuDataTest.*;
import static com.example.restaurant.data.RestaurantDataTest.RESTAUR_ID;
import static com.example.restaurant.data.UserDataTest.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MealRestControllerTest extends AbstractControllerTest{

    @Autowired
    private MealService mealService;

    private static final String REST_URL = MealRestController.REST_URL + '/';
    private static final String REST_URL_MEAL = "/meal/";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_ID + REST_URL_MEAL + MEAL_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(meal));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU_ID + REST_URL_MEAL + MEAL_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID,MENU_ID));
    }

    @Test
    void save() throws Exception {
        Meal newMeal = MealDataTest.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post( REST_URL + MENU_ID + "/meal")
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)))
                .andDo(print())
                .andExpect(status().isCreated());

        Meal created = MEAL_MATCHER.readFromJson(action);
        int newId = created.getId();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(newId,MENU_ID), newMeal);
    }

    @Test
    void update() throws Exception {
        Meal mealUpdate = MealDataTest.getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + MENU_ID + "/meal").contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(mealUpdate)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(mealService.get(MEAL_ID,MENU_ID), mealUpdate);
    }
}
