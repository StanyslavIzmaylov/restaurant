package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Meal;
import com.example.restaurant.model.Menu;

import java.time.LocalDate;


public class MealDataTest {
    public static final MatcherFactory.Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class,"menu");

    public static final int MEAL_ID = 100019;
    public static final int MEAL_NOT_FOUND = 10;

    public static Meal meal = new Meal(MEAL_ID,"Шашлык",350);
    public static Meal meal1 = new Meal(MEAL_ID + 1,"Пиво",150);

    public static Meal getUpdate(){
        return new Meal(MEAL_ID, "Обновленное блюдо",150);
    }

    public static Meal getNew(){
        return new Meal(null,"Новое блюдо", 200);
    }
}
