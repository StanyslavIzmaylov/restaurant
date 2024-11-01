package com.example.restaurant;

import com.example.restaurant.model.User;
import com.example.restaurant.util.AuthorizedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

public class TestUtil {

        public static void mockAuthorize(User user) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(new AuthorizedUser(user), null, user.getRole()));
        }

        public static RequestPostProcessor userHttpBasic(User user) {
            return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
        }

        public static RequestPostProcessor userAuth(User user) {
            return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        }
}
