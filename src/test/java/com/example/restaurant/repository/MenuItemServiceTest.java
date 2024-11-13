package com.example.restaurant.repository;

import com.example.restaurant.data.MenuItemDataTest;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.service.MenuItemService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.example.restaurant.data.MenuDataTest.MENU_ID;
import static com.example.restaurant.data.MenuItemDataTest.MENU_ITEM_ID;
import static com.example.restaurant.data.MenuItemDataTest.MENU_ITEM_MATCHER;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MenuItemServiceTest {

    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void get() {
        MenuItem menuItem = menuItemService.get(MENU_ITEM_ID, MENU_ID);
        MENU_ITEM_MATCHER.assertMatch(MenuItemDataTest.menuItem, menuItem);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuItemService.get(MenuItemDataTest.MENU_ITEM_NOT_FOUND, MENU_ID));
    }

    @Test
    public void update() {
        MenuItem menuItem = MenuItemDataTest.getUpdate();
        menuItemService.update(menuItem, MENU_ITEM_ID, MENU_ID);
        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(MENU_ITEM_ID, MENU_ID), menuItem);
    }

    @Test
    public void save() {
        MenuItem created = menuItemService.save(MenuItemDataTest.getNew(), MENU_ID);
        int newId = created.getId();
        MenuItem newMenu = MenuItemDataTest.getNew();
        newMenu.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newMenu);
        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(newId, MENU_ID), newMenu);
    }

    @Test
    void delete() {
        menuItemService.delete(MENU_ITEM_ID, MENU_ID);
        assertThrows(NotFoundException.class, () -> menuItemService.get(MENU_ITEM_ID, MENU_ID));
    }
}
