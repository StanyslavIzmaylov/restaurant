package com.example.restaurant.repository;

import com.example.restaurant.MenuDataTest;
import com.example.restaurant.RestaurantDataTest;
import com.example.restaurant.model.Menu;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DataJpaMenuRepositoryTest {

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
        assertEquals(MenuDataTest.menu, menu);
    }
    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(MenuDataTest.MENU_NOT_FOUND, RestaurantDataTest.RESTAUR_ID));
    }

    @Test
    public void update() {
      Menu menu = MenuDataTest.getUpdate();
      menuService.update(menu, RestaurantDataTest.RESTAUR_ID);
      assertEquals(menuService.get(MenuDataTest.MENU_ID, RestaurantDataTest.RESTAUR_ID),menu);
    }
}
