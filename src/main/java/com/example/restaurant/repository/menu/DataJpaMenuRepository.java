package com.example.restaurant.repository.menu;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.restaurant.CrudRestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuRepository {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Menu save(Menu menu, int restaurId) {
        if (!menu.isNew() && get(menu.getId(), restaurId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        return crudMenuRepository.save(menu);
    }

    public boolean delete(int id, int restaurId) {
        return crudMenuRepository.delete(id, restaurId) != 0;
    }


    public Menu get(int id, int restaurId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurId)
                .orElse(null);
    }

    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    public List<Menu> getAllWithDate(LocalDate localDate) {
        return crudMenuRepository.getAllWithDate(localDate);
    }

}
