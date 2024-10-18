package com.example.restaurant.repository.meal;

import com.example.restaurant.model.Meal;
import com.example.restaurant.repository.menu.CrudMenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMealRepository {

    private final CrudMealRepository crudMealRepository;
    private final CrudMenuRepository crudMenuRepository;

    public DataJpaMealRepository(CrudMealRepository crudMealRepository, CrudMenuRepository crudMenuRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Transactional
    public Meal save(Meal meal, int menuId) {
        if (!meal.isNew() && get(meal.getId(), menuId) == null) {
            return null;
        }
        meal.setMenu(crudMenuRepository.getReferenceById(menuId));
        return crudMealRepository.save(meal);
    }

    public Meal get(int id, int menuId) {
        return crudMealRepository.findById(id)
                .filter(meal -> meal.getMenu().getId() == menuId).orElse(null);
    }

    public boolean delete(int id, int menuId) {
        return crudMealRepository.delete(id, menuId) != 0;
    }

    public List<Meal> getAll() {
        return crudMealRepository.findAll();
    }
}
