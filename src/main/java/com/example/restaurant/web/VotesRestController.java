package com.example.restaurant.web;

import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.menu.DataJpaMenuRepository;
import com.example.restaurant.repository.votes.DataJpaVotesRepository;
import com.example.restaurant.to.MenuTo;
import com.example.restaurant.to.VotesTo;
import com.example.restaurant.util.MenuUtil;
import com.example.restaurant.util.VotesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VotesRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotesRestController {
    static final String REST_URL = "/rest/votes";

    @Autowired
    private DataJpaVotesRepository votesRepository;

    @Autowired
    private DataJpaMenuRepository dataJpaMenuRepository;

    @GetMapping(path = "/{id}")
    public VotesTo get(@PathVariable int id) {
        Votes votes = votesRepository.get(id);
        return VotesUtil.asTo(votes);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@PathVariable int id) {
        int userId = 100026;
        votesRepository.save(id, userId);
    }

    @GetMapping()
    public List<MenuTo> getAllWithDate() {
        LocalDate localDate = LocalDate.now();
        return MenuUtil.getTos(dataJpaMenuRepository.getAllWithDate(localDate));
    }
}
