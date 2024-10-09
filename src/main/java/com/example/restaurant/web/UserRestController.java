package com.example.restaurant.web;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.restaurant.RestaurantRepository;
import com.example.restaurant.repository.votes.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VotesRepository votesRepository;

    @GetMapping(path = "/{id}")
    public Votes get(@PathVariable int id) {
        return votesRepository.get(id);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@PathVariable int id) {
        int userId = 100019;
        votesRepository.save(userId, id);

    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.getAllWithMenu();
    }


}
