package com.example.restaurant.service;

import com.example.restaurant.model.Meal;
import com.example.restaurant.repository.meal.DataJpaMealRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private final DataJpaMealRepository mealRepository;

    public MealService(DataJpaMealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void delete(int id, int menuId) {
        ValidationUtil.checkNotFoundWithId(mealRepository.delete(id, menuId), id);
    }

    public Meal get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(mealRepository.get(id, menuId), id);
    }

    public void update(Meal meal, int menuId) {
        Assert.notNull(meal, "meal must not be null");
        mealRepository.save(meal, menuId);
    }

    public Meal save(Meal meal, int restaurId) {
        Assert.notNull(meal, "menu must not be null");
        return mealRepository.save(meal, restaurId);
    }
    public List<Meal> getAll() {
        return mealRepository.getAll();
    }
}
