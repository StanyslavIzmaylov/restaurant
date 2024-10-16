package com.example.restaurant.repository.menu;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.restaurant.CrudRestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurId) {
        if (!menu.isNew() && get(menu.getId(), restaurId) == null) {
            return null;
        }
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

    public void deleteAllEveryDay() {
        crudMenuRepository.deleteAll();
    }

    @Override
    @Transactional
    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

}
