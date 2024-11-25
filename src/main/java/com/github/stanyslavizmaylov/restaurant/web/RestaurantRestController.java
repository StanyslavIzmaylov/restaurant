package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.model.Restaurant;
import com.github.stanyslavizmaylov.restaurant.service.RestaurantService;
import com.github.stanyslavizmaylov.restaurant.to.RestaurantTo;
import com.github.stanyslavizmaylov.restaurant.util.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/admin/restaurant";

    @Autowired
    private RestaurantService restaurantService;

    // аменить на get
    @GetMapping(path = REST_URL + "/{id}/with-all-menu")
    public List<Restaurant> getWithAllMenu(@PathVariable int id) {
        return restaurantService.getWithAllMenu(id);
    }

    @GetMapping(path = REST_URL + "/{id}/with-date")
    public Restaurant getWithDate(@PathVariable int id, @RequestParam LocalDate localDate) {
        return restaurantService.getWithDate(id, localDate);
    }

    @DeleteMapping(REST_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        restaurantService.delete(id);
    }

    @PutMapping(path = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated RestaurantTo restaurantTo, @PathVariable int id) {
        Restaurant restaurant = RestaurantUtil.getTo(restaurantTo);
        restaurantService.update(restaurant, id);
    }

    @PostMapping(path = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody @Validated RestaurantTo restaurantTo) {
        Restaurant restaurant = RestaurantUtil.getTo(restaurantTo);
        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(path = "rest/restaurant" + "/all-with-menu-to-day")
    public List<Restaurant> getWithMenuToDay() {
        LocalDate localDate = LocalDate.now();
        return restaurantService.getWithMenuAndWithDate(localDate);
    }

    @GetMapping(path = REST_URL + "/all-with-date")
    public List<Restaurant> getWithMenuAndWithDate(@RequestParam LocalDate localDate) {
        return restaurantService.getWithMenuAndWithDate(localDate);
    }
}
