package com.example.restaurant.web;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import com.example.restaurant.repository.votes.VotesRepository;
import com.example.restaurant.to.VotesTo;
import com.example.restaurant.util.VotesUtil;
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
    private DataJpaRestaurantRepository dataJpaRestaurantRepository;

    @Autowired
    private VotesRepository votesRepository;

    @GetMapping(path = "/{id}")
    public VotesTo get(@PathVariable int id) {
        Votes votes = votesRepository.get(id);
        return VotesUtil.asTo(votes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@RequestBody Votes votes) {
        int userId = 100019;
        votesRepository.save(votes, userId);
    }

    @GetMapping()
    public List<Restaurant> getAllWithDate() {
        return dataJpaRestaurantRepository.getAll();
    }


}
