package com.example.restaurant.repository;

import com.example.restaurant.data.MenuItemDataTest;
import com.example.restaurant.data.MenuDataTest;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.service.MenuItemService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MenuItemServiceTest {

    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void get() {
        MenuItem menuItem = menuItemService.get(MenuItemDataTest.MENU_ITEM_ID, MenuDataTest.MENU_ID);
        MenuItemDataTest.MENU_ITEM_MATCHER.assertMatch(MenuItemDataTest.menuItem, menuItem);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuItemService.get(MenuItemDataTest.MENU_ITEM_NOT_FOUND,MenuDataTest.MENU_ID));
    }

    @Test
    public void update() {
        MenuItem menuItem = MenuItemDataTest.getUpdate();
        menuItemService.update(menuItem, MenuDataTest.MENU_ID);
        MenuItemDataTest.MENU_ITEM_MATCHER.assertMatch(menuItemService.get(MenuItemDataTest.MENU_ITEM_ID, MenuDataTest.MENU_ID), menuItem);
    }

    @Test
    public void save() {
        MenuItem created = menuItemService.save(MenuItemDataTest.getNew(),MenuDataTest.MENU_ID);
        int newId = created.getId();
        MenuItem newMenu = MenuItemDataTest.getNew();
        newMenu.setId(newId);
        MenuItemDataTest.MENU_ITEM_MATCHER.assertMatch(created, newMenu);
        MenuItemDataTest.MENU_ITEM_MATCHER.assertMatch(menuItemService.get(newId,MenuDataTest.MENU_ID), newMenu);
    }

    @Test
    void delete() {
        menuItemService.delete(MenuItemDataTest.MENU_ITEM_ID, MenuDataTest.MENU_ID);
        assertThrows(NotFoundException.class, () -> menuItemService.get(MenuItemDataTest.MENU_ITEM_ID, MenuDataTest.MENU_ID));
    }

}
