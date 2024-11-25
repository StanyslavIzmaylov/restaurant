package com.github.stanyslavizmaylov.restaurant.util;

import com.github.stanyslavizmaylov.restaurant.model.Menu;
import com.github.stanyslavizmaylov.restaurant.to.MenuTo;

public class MenuUtil {
    public static Menu getTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getMenuDate());
    }
}
