package com.example.restaurant.web;

import com.example.restaurant.data.MenuDataTest;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Vote;
import com.example.restaurant.service.VoteService;
import com.example.restaurant.util.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.restaurant.TestUtil.userHttpBasic;
import static com.example.restaurant.data.MenuDataTest.MENU_ID;
import static com.example.restaurant.data.MenuDataTest.MENU_MATCHER;
import static com.example.restaurant.data.RestaurantDataTest.RESTAUR_ID;
import static com.example.restaurant.data.UserDataTest.*;
import static com.example.restaurant.data.UserDataTest.user;
import static com.example.restaurant.data.VoteDataTest.*;
import static com.example.restaurant.data.VoteDataTest.getNew;
import static com.example.restaurant.data.VoteDataTest.getUpdate;
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
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL)
                .with(userHttpBasic(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.getId();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newId,USER_ID), newVote);
    }

    @Test
    void update() throws Exception {
        Vote voteUpdate = getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(JsonUtil.writeValue(voteUpdate)))
                .andDo(print())
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteService.get(VOTE_ID,USER_ID), voteUpdate);
    }
}
