package com.packtpub.onlineauction.controller;

import com.packtpub.onlineauction.service.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void testAdminAccessWithToken() throws Exception {

        // Load user from DB or in-memory store
        UserDetails admin = userDetailsService.loadUserByUsername("admin");

        // Generate JWT token
        String token = "Bearer " + jwtService.generateToken(admin);

        // Perform request with Authorization header
        mockMvc.perform(get("/admin")
                        .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    public void testUserAccessWithToken() throws Exception {

        // Load user from DB or in-memory store
        UserDetails user = userDetailsService.loadUserByUsername("user");

        // Generate JWT token
        String token = "Bearer " + jwtService.generateToken(user);

        // Perform request with Authorization header
        mockMvc.perform(get("/admin")
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAccessWithEmptyToken() throws Exception {

        String token = "";

        mockMvc.perform(get("/admin")
                        .header("Authorization", token))
                .andExpect(status().isUnauthorized());
    }

}
