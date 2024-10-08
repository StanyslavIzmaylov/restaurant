package com.example.restaurant.repository.menu;

import com.example.restaurant.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurId);

    boolean delete(int id, int restaurId);

    Menu get(int id, int restaurId);

    List<Menu> getAll();
}
