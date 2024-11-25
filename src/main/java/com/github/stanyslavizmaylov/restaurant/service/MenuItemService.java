package com.github.stanyslavizmaylov.restaurant.service;

import com.github.stanyslavizmaylov.restaurant.model.MenuItem;
import com.github.stanyslavizmaylov.restaurant.repository.CrudMenuItemRepository;
import com.github.stanyslavizmaylov.restaurant.repository.CrudMenuRepository;
import com.github.stanyslavizmaylov.restaurant.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MenuItemService {

    private final CrudMenuItemRepository menuItemRepository;
    private final CrudMenuRepository crudMenuRepository;

    public MenuItemService(CrudMenuItemRepository MenuItemRepository, CrudMenuRepository crudMenuRepository) {
        this.menuItemRepository = MenuItemRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    public void delete(int id, int menuId) {
        ValidationUtil.checkNotFoundWithId(menuItemRepository.delete(id, menuId), id);
    }

    public MenuItem get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(menuItemRepository.findById(id)
                .filter(meal -> meal.getMenu().getId() == menuId).orElse(null), id);
    }

    @Transactional
    public void update(MenuItem menuItem, int menuItemId, int menuId) {
        Assert.notNull(menuItem, "meal must not be null");
        ValidationUtil.assureIdConsistent(menuItem, menuItemId);
        ValidationUtil.checkNotFoundWithId(get(menuItemId, menuId), menuItemId);
        menuItem.setMenu(crudMenuRepository.getReferenceById(menuId));
        menuItemRepository.save(menuItem);
    }

    @Transactional
    public MenuItem save(MenuItem menuItem, int menuId) {
        Assert.notNull(menuItem, "menu must not be null");
        if (!menuItem.isNew() && get(menuItem.getId(), menuId) != null) {
            return null;
        }
        menuItem.setMenu(crudMenuRepository.getReferenceById(menuId));
        return menuItemRepository.save(menuItem);
    }
}
