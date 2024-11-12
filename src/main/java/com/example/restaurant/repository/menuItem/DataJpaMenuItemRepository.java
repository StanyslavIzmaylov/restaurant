package com.example.restaurant.repository.menuItem;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.menu.CrudMenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMenuItemRepository {

    private final CrudMenuItemRepository crudMenuItemRepository;
    private final CrudMenuRepository crudMenuRepository;

    public DataJpaMenuItemRepository(CrudMenuItemRepository crudMenuItemRepository, CrudMenuRepository crudMenuRepository) {
        this.crudMenuItemRepository = crudMenuItemRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Transactional
    public MenuItem save(MenuItem menuItem, int menuId) {
        if (!menuItem.isNew() && get(menuItem.getId(), menuId) == null) {
            return null;
        }
        menuItem.setMenu(crudMenuRepository.getReferenceById(menuId));
        return crudMenuItemRepository.save(menuItem);
    }

    public MenuItem get(int id, int menuId) {
        return crudMenuItemRepository.findById(id)
                .filter(meal -> meal.getMenu().getId() == menuId).orElse(null);
    }

    public boolean delete(int id, int menuId) {
        return crudMenuItemRepository.delete(id, menuId) != 0;
    }

    public List<MenuItem> getAll() {
        return crudMenuItemRepository.findAll();
    }
}
