package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.MenuItem;


public class MenuItemDataTest {
    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class,"menu");

    public static final int MENU_ITEM_ID = 100019;
    public static final int MENU_ITEM_NOT_FOUND = 10;

    public static MenuItem menuItem = new MenuItem(MENU_ITEM_ID,"Шашлык",350);
    public static MenuItem menuItem1 = new MenuItem(MENU_ITEM_ID + 1,"Пиво",150);

    public static MenuItem getUpdate(){
        return new MenuItem(MENU_ITEM_ID, "Обновленное блюдо",150);
    }

    public static MenuItem getNew(){
        return new MenuItem(null,"Новое блюдо", 200);
    }
}
