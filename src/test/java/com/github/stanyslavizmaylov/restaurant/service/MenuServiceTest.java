package com.github.stanyslavizmaylov.restaurant.service;

import com.github.stanyslavizmaylov.restaurant.data.MenuDataTest;
import com.github.stanyslavizmaylov.restaurant.model.Menu;
import com.github.stanyslavizmaylov.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.github.stanyslavizmaylov.restaurant.data.MenuDataTest.MENU_ID;
import static com.github.stanyslavizmaylov.restaurant.data.RestaurantDataTest.RESTAUR_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void delete() {
        menuService.delete(MENU_ID, RESTAUR_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID, RESTAUR_ID));
    }

    @Test
    public void get() {
        Menu menu = menuService.get(MENU_ID, RESTAUR_ID);
        MenuDataTest.MENU_MATCHER.assertMatch(MenuDataTest.menu, menu);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(MenuDataTest.MENU_NOT_FOUND, RESTAUR_ID));
    }

    @Test
    public void update() {
        Menu menu = MenuDataTest.getUpdate();
        menuService.update(menu, RESTAUR_ID, MENU_ID);
        MenuDataTest.MENU_MATCHER.assertMatch(menuService.get(MENU_ID, RESTAUR_ID), menu);
    }

    @Test
    public void save() {
        Menu created = menuService.save(MenuDataTest.getNew(), RESTAUR_ID);
        int newId = created.getId();
        Menu newMenu = MenuDataTest.getNew();
        newMenu.setId(newId);
        MenuDataTest.MENU_MATCHER.assertMatch(created, newMenu);
        MenuDataTest.MENU_MATCHER.assertMatch(menuService.get(newId, RESTAUR_ID), newMenu);
    }
}
