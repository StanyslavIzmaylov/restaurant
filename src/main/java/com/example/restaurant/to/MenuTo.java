package com.example.restaurant.to;

import com.example.restaurant.model.MenuItem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MenuTo implements Serializable {
    private Integer restaurantId;

    private String restaurantName;

    private Integer menuId;

    private LocalDate localDate;

    private List<MenuItem> menuItemList;

    public MenuTo(Integer restaurantId, String restaurantName, Integer menuId, LocalDate localDate, List<MenuItem> menuItemList) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuId = menuId;
        this.localDate = localDate;
        this.menuItemList = menuItemList;
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

    public List<MenuItem> getMenuItem() {
        return menuItemList;
    }

    public void setMenuItem(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
