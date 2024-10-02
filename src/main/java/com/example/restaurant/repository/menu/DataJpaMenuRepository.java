package com.example.restaurant.repository.menu;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.restaurant.CrudRestaurantRepository;

import java.util.List;

public class DataJpaMenuRepository implements MenuRepository {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Menu save(Menu menu, int restaurId) {

        // проверить menu.getId() int или Intenger
//        if (menu.getId() == null && get(menu.getId(), restaurId) == null) {
//            return null;
//        }
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id, int restaurId) {
        return crudMenuRepository.delete(id, restaurId) != 0;
    }

    @Override
    public Menu get(int id, int restaurId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurId)
                .orElse(null);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }
}
