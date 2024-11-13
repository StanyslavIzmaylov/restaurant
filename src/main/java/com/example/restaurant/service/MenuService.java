package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.CrudMenuRepository;
import com.example.restaurant.repository.CrudRestaurantRepository;
import com.example.restaurant.to.MenuTo;
import com.example.restaurant.util.MenuUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.example.restaurant.util.ValidationUtil.assureIdConsistent;
import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuService(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    public void delete(int id, int restaurId) {
        checkNotFoundWithId(crudMenuRepository.delete(id, restaurId), id);
    }

    public Menu get(int id, int restaurId) {
        return checkNotFoundWithId(crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurId)
                .orElse(null), id);
    }
    @Transactional
    public void update(Menu menu, int restaurId, int id) {
        Assert.notNull(menu, "menu must not be null");
        assureIdConsistent(menu, id);
        checkNotFoundWithId(get(id, restaurId), id);
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        crudMenuRepository.save(menu);
    }

    @Transactional
    public Menu save(Menu menu, int restaurId) {
        Assert.notNull(menu, "menu must not be null");
        if (!menu.isNew() && get(menu.getId(), restaurId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        return crudMenuRepository.save(menu);
    }

    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }
    
    public List<MenuTo> getAllWithDate(LocalDate localDate) {
        return MenuUtil.getTos(crudMenuRepository.getAllWithDate(localDate));
    }
}
