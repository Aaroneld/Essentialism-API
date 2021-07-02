package com.merrifield.Essentialism.API.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merrifield.Essentialism.API.config.SecurityConfig;
import com.merrifield.Essentialism.API.controllers.UserController;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserValuesId;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.UserModels.UserMinimum;
import com.merrifield.Essentialism.API.services.UserService;
import com.merrifield.Essentialism.API.services.impl.SecurityUserServiceImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.security.auth.message.config.AuthConfig;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = {UserController.class, SecurityConfig.class, SecurityUserServiceImpl.class, AuthConfig.class})
public class UserControllerUnitTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    SecurityUserServiceImpl securityUserService;

    @Autowired
    MockMvc mockMvc;


    @Test
    void whenUserRoleGetAllUsers_thenReturns200() throws Exception {

        UserMinimum user1 = new UserMinimum("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");
        UserMinimum user2 = new UserMinimum("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");

        doReturn(List.of(user1, user2)).when(userService).findAllUsers();

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(user1, user2))));

    }

    @Test
    void whenUserRoleGetAllUsers_thenUserServiceCallsFindAllUsersOnce() throws Exception {

        UserMinimum user1 = new UserMinimum("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");
        UserMinimum user2 = new UserMinimum("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");

        doReturn(List.of(user1, user2)).when(userService).findAllUsers();

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    void whenUserRoleFindByExistingId_thenReturns200() throws Exception {

        User user = new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");
        user.setId(1);

        doReturn(user).when(userService).findUserById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(user)));

    }


    @Test
    void whenUserRoleFindByExistingId_thenUserServiceCallsFindUserByIdOnce() throws Exception {

        User user = new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");
        user.setId(1);

        doReturn(user).when(userService).findUserById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(userService, times(1)).findUserById(anyLong());
    }

    @Test
    void whenUserRoleFindByExistingEmailSubstring_thenReturns200() throws Exception {

        User user1 = new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");
        User user2 = new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");

        user1.setId(1);
        user2.setId(2);

        doReturn(List.of(user1, user2)).when(userService).findUserByEmailContaining(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/email/{email}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(user1, user2))));

    }

    @Test
    void whenUserRoleFindByExistingEmailSubstring_thenUserServiceCallsFindUserByEmailContainingOnce() throws Exception {

        User user1 = new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");
        User user2 = new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");

        user1.setId(1);
        user2.setId(2);

        doReturn(List.of(user1, user2)).when(userService).findUserByEmailContaining(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/email/{email}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(userService, times(1)).findUserByEmailContaining(any(String.class));
    }

    @Test
    void whenUserUpdatesCorrectly_thenReturns202() throws Exception {

       UserMinimum user = new UserMinimum("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");

        doReturn(12L).when(userService).updateUser(any(User.class), anyLong());

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("12"));
    }

    @Test
    void whenUserUpdates_thenUserServiceCallsUpdateUserOnce() throws Exception {

        UserMinimum user = new UserMinimum("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000");

        doReturn(12L).when(userService).updateUser(any(User.class), anyLong());

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)));


        verify(userService, times(1)).updateUser(any(User.class), anyLong());
    }

    @Test
    void whenUserUpdatesUserCoreInformationWithIncorrectOrMissingJSONParameters_thenReturns400() throws Exception {

        UserMinimum user = new UserMinimum("username", "password", null, null, "email@email.com", "000-0000-000");

        doReturn(12L).when(userService).updateUser(any(User.class), anyLong());

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void whenUserAddsOwnUserValueCorrectly_then201() throws Exception {

        UserValuesId userValuesId = new UserValuesId(1,2);

        doReturn(Set.of("Acceptance", "Compassion")).when(userService).addUserValue(any(UserValuesId.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userValuesId)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(Set.of("Acceptance", "Compassion"))));
    }

    @Test
    void whenUserAddsOwnUserValue_thenUserServiceCallsAddUserValueOnce() throws Exception {

        UserValuesId userValuesId = new UserValuesId(1,2);

        doReturn(Set.of("Acceptance", "Compassion")).when(userService).addUserValue(any(UserValuesId.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userValuesId)));

        verify(userService, times(1)).addUserValue(any(UserValuesId.class));
    }

    @Test
    void whenUserAttemptsToAddNewUserValueResourceWithIncorrectOrMissingJSONParameters_thenReturns400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content("{'user': 1}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void whenUserDeletesThemselvesCorrectly_thenReturns204() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("Success"));
    }

    @Test
    void whenUserDeletesThemselves_thenUserServiceCallsDeleteUserOnce() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(userService, times(1)).deleteUser(anyLong());
    }


    @Test
    void whenUserDeletesOwnUserValueCorrectly_thenReturns204() throws Exception {

        UserValuesId userValuesId = new UserValuesId(1,2);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userValuesId)))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("Success"));
    }

    @Test
    void whenUserDeletesThemselves_thenUserServiceCallsDeleteUserValueOnce() throws Exception {

        UserValuesId userValuesId = new UserValuesId(1,2);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userValuesId)));

        verify(userService, times(1)).deleteUserValue(any(UserValuesId.class));
    }

    @Test
    void whenUserAttemptsToDeleteUserValueResourceWithIncorrectOrMissingJSONParameters_thenReturns400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content("{'user': 1}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }
}
