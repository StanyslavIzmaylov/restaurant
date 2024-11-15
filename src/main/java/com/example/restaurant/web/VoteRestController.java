package com.example.restaurant.web;

import com.example.restaurant.model.Vote;
import com.example.restaurant.service.VoteService;
import com.example.restaurant.util.AuthorizedUser;
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

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService voteService;

    @GetMapping(path = "/{id}")
    public Vote get(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        return voteService.get(id,authUser.getId());
    }

    @GetMapping(path = "/{id}/date-to-day")
    public Vote getWithDateToDay(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        return voteService.getWithDateToDay(id,authUser.getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated Vote vote, @PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        vote.setVoteDate(LocalDate.now());
        voteService.update(vote,id,authUser.getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> save(@RequestBody @Validated Vote vote, @AuthenticationPrincipal AuthorizedUser authUser) {
        vote.setVoteDate(LocalDate.now());
        Vote created = voteService.save(vote, authUser.getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
