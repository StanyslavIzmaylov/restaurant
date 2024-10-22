package com.example.restaurant.repository;

import com.example.restaurant.data.MenuDataTest;
import com.example.restaurant.data.RestaurantDataTest;
import com.example.restaurant.model.Menu;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void delete() {
        menuService.delete(MenuDataTest.MENU_ID, RestaurantDataTest.RESTAUR_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(MenuDataTest.MENU_ID, RestaurantDataTest.RESTAUR_ID));
    }

    @Test
    public void get() {
        Menu menu = menuService.get(MenuDataTest.MENU_ID, RestaurantDataTest.RESTAUR_ID);
        MenuDataTest.MENU_MATCHER.assertMatch(MenuDataTest.menu, menu);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(MenuDataTest.MENU_NOT_FOUND, RestaurantDataTest.RESTAUR_ID));
    }

    @Test
    public void update() {
        Menu menu = MenuDataTest.getUpdate();
        menuService.update(menu, RestaurantDataTest.RESTAUR_ID);
        MenuDataTest.MENU_MATCHER.assertMatch(menuService.get(MenuDataTest.MENU_ID, RestaurantDataTest.RESTAUR_ID), menu);
    }

    @Test
    public void save() {
        Menu created = menuService.save(MenuDataTest.getNew(), RestaurantDataTest.RESTAUR_ID);
        int newId = created.getId();
        Menu newMenu = MenuDataTest.getNew();
        newMenu.setId(newId);
        MenuDataTest.MENU_MATCHER.assertMatch(created, newMenu);
        MenuDataTest.MENU_MATCHER.assertMatch(menuService.get(newId, RestaurantDataTest.RESTAUR_ID), newMenu);
    }
}
