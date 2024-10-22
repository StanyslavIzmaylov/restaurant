package com.example.restaurant.web;

import com.example.restaurant.model.Meal;
import com.example.restaurant.repository.meal.DataJpaMealRepository;
import com.example.restaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {

    static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MealService mealService;

    @GetMapping(path = "/{menuId}/meal/{mealId}")
    public Meal get(@PathVariable int menuId, @PathVariable int mealId) {
        return mealService.get(mealId, menuId);
    }

    @DeleteMapping("/{menuId}/meal/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId, @PathVariable int mealId) {
        mealService.delete(mealId, menuId);
    }

    @PutMapping(value = "/{menuId}/meal", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int menuId) {
        mealService.save(meal, menuId);
    }

    @PostMapping(value = "/{menuId}/meal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable int menuId) {
        Meal created = mealService.save(meal, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/meals")
    public List<Meal> getAll() {
        return mealService.getAll();
    }
}
