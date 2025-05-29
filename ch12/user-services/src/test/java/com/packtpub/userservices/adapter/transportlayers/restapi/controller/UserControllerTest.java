package com.packtpub.userservices.adapter.transportlayers.restapi.controller;

import com.packtpub.userservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.userservices.internal.entity.Role;
import com.packtpub.userservices.internal.entity.User;
import com.packtpub.userservices.internal.usecases.GetUserRolesUseCase;
import com.packtpub.userservices.internal.usecases.GetUsersUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Since Spring Boot 3.4.0, both @MockBean and @MockBeans have been deprecated
     * in favor of the new @MockitoBean annotation, which should be used going forward.
     * This change was motivated to clarify the test library in use.
     * Many developers were confused about whether @MockBean was Spring’s own mocking
     * mechanism or tied to Mockito — so Spring 3.4 made the
     * dependency explicit by introducing @MockitoBean.
     */

    @MockitoBean
    private GetUsersUseCase getUsersUseCase;

    @MockitoBean
    private AuthenticationRestApi authenticationRestApi;

    @MockitoBean
    private GetUserRolesUseCase getUserRolesUseCase;

    @Test
    void getUsers_shouldReturnListOfUsers_whenUsersExist() throws Exception {

        List<User> users = List.of(
                new User(1L, "Alice", "alice@example.com", "123456789", "City", "State", "Country", Set.of(new Role(1L, "ROLE_USER"))),
                new User(2L, "Bob", "bob@example.com", "987654321", "City", "State", "Country", Set.of(new Role(2L,"ROLE_ADMIN")))
        );

        when(getUsersUseCase.execute()).thenReturn(users);

        mockMvc.perform(get("/v1/users") // adjust endpoint if @RequestMapping is present
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].email").value("bob@example.com"));
    }

    @Test
    void getUsers_shouldReturnNotFound_whenNoUsersExist() throws Exception {
        when(getUsersUseCase.execute()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
