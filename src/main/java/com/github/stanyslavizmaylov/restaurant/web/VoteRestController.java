package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.model.Vote;
import com.github.stanyslavizmaylov.restaurant.service.VoteService;
import com.github.stanyslavizmaylov.restaurant.to.VoteTo;
import com.github.stanyslavizmaylov.restaurant.util.AuthorizedUser;
import com.github.stanyslavizmaylov.restaurant.util.VoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService voteService;

    @GetMapping(path = REST_URL + "/{id}")
    public Vote get(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        return voteService.get(id, authUser.getId());
    }

    @GetMapping(path = REST_URL + "/date-to-day")
    public Vote getWithDateToDay(@AuthenticationPrincipal AuthorizedUser authUser) {
        return voteService.getWithDateToDay(authUser.getId());
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated VoteTo voteTo, @PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        Vote vote = VoteUtil.getTo(voteTo);
        vote.setVoteDate(LocalDate.now());
        vote.setLocalTime(LocalTime.now());
        voteService.update(vote, id, authUser.getId(), voteTo.getRestaurantId());
    }

    @PostMapping(path = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> save(@RequestBody @Validated VoteTo voteTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        Vote vote = VoteUtil.getTo(voteTo);
        vote.setVoteDate(LocalDate.now());
        vote.setLocalTime(LocalTime.now());
        Vote created = voteService.save(vote, authUser.getId(), voteTo.getRestaurantId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(REST_URL)
    public List<Vote> getAllWithUser(@AuthenticationPrincipal AuthorizedUser authUser) {
        return voteService.getAllWithUser(authUser.getId());
    }

    @GetMapping("/rest/admin/vote/all-with-date")
    public List<Vote> geWithDate(@RequestParam LocalDate localDate) {
        return voteService.geWithDate(localDate);
    }
}
