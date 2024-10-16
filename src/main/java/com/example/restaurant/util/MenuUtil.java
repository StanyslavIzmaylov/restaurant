package com.example.restaurant.util;

import com.example.restaurant.model.Menu;
import com.example.restaurant.to.MenuTo;

import java.util.Collection;
import java.util.List;

public class MenuUtil {

    public static MenuTo asTo(Menu menu){
        return new MenuTo(menu.getRestaurant().getId(),menu.getRestaurant().getName(),menu.getId(),menu.getDate(),menu.getMeals());
    }

    public static List<MenuTo> getTos(Collection<Menu> menus){
      return menus.stream().map(MenuUtil::asTo).toList();
    }
}
