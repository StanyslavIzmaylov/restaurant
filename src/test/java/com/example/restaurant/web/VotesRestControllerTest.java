package com.example.restaurant.web;

import com.example.restaurant.data.RestaurantDataTest;
import com.example.restaurant.model.Votes;
import com.example.restaurant.service.VotesService;
import com.example.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurant.TestUtil.userHttpBasic;
import static com.example.restaurant.data.UserDataTest.user;
import static com.example.restaurant.data.VotesDataTest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VotesRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VotesRestController.REST_URL + '/';

    @Autowired
    private VotesService votesService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTES_ID)
                .with(userHttpBasic(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTES_MATCHER.contentJson(votes));
    }

    @Test
    void save() throws Exception {
        Votes newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RestaurantDataTest.RESTAUR_ID)
                .with(userHttpBasic(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isCreated());

        Votes created = VOTES_MATCHER.readFromJson(action);
        int newId = created.getId();
        newVote.setId(newId);
        VOTES_MATCHER.assertMatch(created, newVote);
        VOTES_MATCHER.assertMatch(votesService.get(newId), newVote);
    }

}
