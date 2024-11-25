package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.model.Vote;
import com.github.stanyslavizmaylov.restaurant.service.VoteService;
import com.github.stanyslavizmaylov.restaurant.to.VoteTo;
import com.github.stanyslavizmaylov.restaurant.util.DateTimeUtil;
import com.github.stanyslavizmaylov.restaurant.util.VoteUtil;
import com.github.stanyslavizmaylov.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static com.github.stanyslavizmaylov.restaurant.TestUtil.userHttpBasic;
import static com.github.stanyslavizmaylov.restaurant.data.RestaurantDataTest.RESTAUR_ID;
import static com.github.stanyslavizmaylov.restaurant.data.UserDataTest.*;
import static com.github.stanyslavizmaylov.restaurant.data.VoteDataTest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService voteService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID)
                .with(userHttpBasic(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote));
    }

    @Test
    void save() throws Exception {
        VoteTo voteTo = new VoteTo(null,RESTAUR_ID);
        Vote newVote = VoteUtil.getTo(voteTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL)
                .with(userHttpBasic(user2))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.getId();
        newVote.setId(newId);
//        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newId, USER_ID + 2), created);
    }

    @Test
    void updateBeforeEleven() throws Exception {
        VoteTo voteTo = new VoteTo(VOTE_ID, RESTAUR_ID);
        Vote voteUpdate = VoteUtil.getTo(voteTo);

        DateTimeUtil.setTimeNow(LocalTime.of(10, 0));
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(JsonUtil.writeValue(voteTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteService.get(VOTE_ID, USER_ID), voteUpdate);
        DateTimeUtil.setTimeNow(LocalTime.now());
    }

    @Test
    void updateAfterEleven() throws Exception {
        VoteTo voteTo = new VoteTo(VOTE_ID, RESTAUR_ID);
        Vote voteUpdate = VoteUtil.getTo(voteTo);

        DateTimeUtil.setTimeNow(LocalTime.of(12, 0));
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(JsonUtil.writeValue(voteTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        DateTimeUtil.setTimeNow(LocalTime.now());
    }
}
