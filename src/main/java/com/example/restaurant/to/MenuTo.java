package com.example.restaurant.to;

import com.example.restaurant.model.MenuItem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class MenuTo implements Serializable {
    private Integer restaurantId;

    private String restaurantName;

    private Integer menuId;

    private LocalDate localDate;

    private Set<MenuItem> menuItems;

    public MenuTo(Integer restaurantId, String restaurantName, Integer menuId, LocalDate localDate, Set<MenuItem> menuItems) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuId = menuId;
        this.localDate = localDate;
        this.menuItems = menuItems;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Set<MenuItem> getMenuItem() {
        return menuItems;
    }

    public void setMenuItem(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
