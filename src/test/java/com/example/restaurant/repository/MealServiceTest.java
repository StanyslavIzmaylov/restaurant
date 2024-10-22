package com.example.restaurant.repository;

import com.example.restaurant.data.MealDataTest;
import com.example.restaurant.data.MenuDataTest;
import com.example.restaurant.model.Meal;
import com.example.restaurant.service.MealService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(MealDataTest.MEAL_ID, MenuDataTest.MENU_ID);
        MealDataTest.MEAL_MATCHER.assertMatch(MealDataTest.meal, meal);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.get(MealDataTest.MEAL_NOT_FOUND,MenuDataTest.MENU_ID));
    }

    @Test
    public void update() {
        Meal meal = MealDataTest.getUpdate();
        mealService.update(meal, MenuDataTest.MENU_ID);
        MealDataTest.MEAL_MATCHER.assertMatch(mealService.get(MealDataTest.MEAL_ID, MenuDataTest.MENU_ID), meal);
    }

    @Test
    public void save() {
        Meal created = mealService.save(MealDataTest.getNew(),MenuDataTest.MENU_ID);
        int newId = created.getId();
        Meal newMenu = MealDataTest.getNew();
        newMenu.setId(newId);
        MealDataTest.MEAL_MATCHER.assertMatch(created, newMenu);
        MealDataTest.MEAL_MATCHER.assertMatch(mealService.get(newId,MenuDataTest.MENU_ID), newMenu);
    }

    @Test
    void delete() {
        mealService.delete(MealDataTest.MEAL_ID, MenuDataTest.MENU_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(MealDataTest.MEAL_ID, MenuDataTest.MENU_ID));
    }

}
