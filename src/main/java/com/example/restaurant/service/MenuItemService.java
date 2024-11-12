package com.example.restaurant.service;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.menuItem.DataJpaMenuItemRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private final DataJpaMenuItemRepository dishRepository;

    public MenuItemService(DataJpaMenuItemRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void delete(int id, int menuId) {
        ValidationUtil.checkNotFoundWithId(dishRepository.delete(id, menuId), id);
    }

    public MenuItem get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(dishRepository.get(id, menuId), id);
    }

    public void update(MenuItem menuItem, int menuId) {
        Assert.notNull(menuItem, "meal must not be null");
        dishRepository.save(menuItem, menuId);
    }

    public MenuItem save(MenuItem menuItem, int restaurId) {
        Assert.notNull(menuItem, "menu must not be null");
        return dishRepository.save(menuItem, restaurId);
    }
    public List<MenuItem> getAll() {
        return dishRepository.getAll();
    }
}
