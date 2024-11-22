package com.example.restaurant.util;

import com.example.restaurant.model.Menu;
import com.example.restaurant.to.MenuTo;

public class MenuUtil {
    public static Menu getTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getMenuDate());
    }
}
