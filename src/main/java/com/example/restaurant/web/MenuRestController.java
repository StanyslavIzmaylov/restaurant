package com.example.restaurant.web;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.menu.DataJpaMenuRepository;
import com.example.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private MenuService menuService;

    @GetMapping(path = "/{restaurId}/menu/{menuId}")
    public Menu get(@PathVariable int restaurId, @PathVariable int menuId) {
        return menuService.get(menuId, restaurId);
    }

    @DeleteMapping("/{restaurId}/menu/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurId, @PathVariable int menuId) {
        menuService.delete(menuId, restaurId);
    }

    @PutMapping(value = "/{restaurId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @PathVariable int restaurId) {
        menuService.save(menu, restaurId);
    }

    @PostMapping(value = "/{restaurId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable int restaurId) {
        Menu created = menuService.save(menu, restaurId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/menus")
    public List<Menu> getAll() {
        return menuService.getAll();
    }
}
