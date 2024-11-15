package com.example.restaurant.repository;

import com.example.restaurant.data.VoteDataTest;
import com.example.restaurant.model.Vote;
import com.example.restaurant.service.VoteService;
import com.example.restaurant.util.DateTimeUtil;
import com.example.restaurant.util.exeption.NotFoundException;
import com.example.restaurant.util.exeption.TimeRangeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;

import static com.example.restaurant.data.UserDataTest.USER_ID;
import static com.example.restaurant.data.VoteDataTest.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void get() {
        Vote vote = voteService.get(VOTE_ID, USER_ID);
        VoteDataTest.VOTE_MATCHER.assertMatch(VoteDataTest.vote, vote);
    }

    @Test
    void delete() {
        voteService.delete(VOTE_ID);
        assertThrows(NotFoundException.class, () -> voteService.get(VOTE_ID, USER_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.get(VOTE_NOT_FOUND, USER_ID));
    }

    @Test
    public void update() {
        Vote vote = getUpdate();
        voteService.update(vote, VOTE_ID, USER_ID);
        VoteDataTest.VOTE_MATCHER.assertMatch(voteService.get(VOTE_ID, USER_ID), vote);
    }

    @Test
    public void updateBeforeEleven() {
        Vote vote = getUpdateWithToDayDate();
        DateTimeUtil.setTimeNow(LocalTime.of(10, 00));
        voteService.update(vote, VOTE_ID + 1, USER_ID + 1);
        VOTE_MATCHER.assertMatch(voteService.get(VOTE_ID + 1, USER_ID + 1), vote);
    }

    @Test
    public void updateAfterEleven() {
        Vote vote = getUpdateWithToDayDate();
        DateTimeUtil.setTimeNow(LocalTime.of(12, 00));
        assertThrows(TimeRangeException.class, () -> voteService.update(vote, VOTE_ID + 1, USER_ID + 1));
    }

    @Test
    public void save() {
        Vote created = voteService.save(getNew(), USER_ID);
        int newId = created.getId();
        Vote newVote = getNew();
        newVote.setId(newId);
        VoteDataTest.VOTE_MATCHER.assertMatch(created, newVote);
        VoteDataTest.VOTE_MATCHER.assertMatch(voteService.get(newId, USER_ID), newVote);
    }
}
