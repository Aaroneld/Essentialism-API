package com.merrifield.Essentialism.API.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merrifield.Essentialism.API.config.SecurityConfig;
import com.merrifield.Essentialism.API.controllers.ValueController;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValueId;
import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import com.merrifield.Essentialism.API.models.ProjectModels.ProjectMinimum;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.Value;
import com.merrifield.Essentialism.API.services.ValueService;
import com.merrifield.Essentialism.API.services.impl.SecurityUserServiceImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.security.auth.message.config.AuthConfig;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {ValueController.class, SecurityConfig.class, SecurityUserServiceImpl.class, AuthConfig.class})
public class ValueControllerUnitTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ValueService valueService;

    @MockBean
    SecurityUserServiceImpl securityUserService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenUserRoleGetsAllValues_thenReturns200() throws Exception {

        Value value1 = new Value(1, "Acceptance");
        Value value2 = new Value(1, "Compassion");

        doReturn(List.of(value1, value2)).when(valueService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/values")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(value1, value2))));

    }

    @Test
    void whenUserRoleGetsAllValues_thenValuesServiceCallsFindAllOnce() throws Exception {

        Value value1 = new Value(1, "Acceptance");
        Value value2 = new Value(1, "Compassion");

        doReturn(List.of(value1, value2)).when(valueService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/values")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(valueService, times(1)).findAll();
    }

    @Test
    void whenUserRoleFindsValueByExistingId_thenReturns200() throws Exception {

        Value value = new Value(1, "Acceptance");

        doReturn(value).when(valueService).findValueById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/values/{id}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(value)));

    }


    @Test
    void whenUserRoleFindsValueByExistingId_thenValueServiceCallsFindValueByIdOnce() throws Exception {

        Value value = new Value(1, "Acceptance");

        doReturn(value).when(valueService).findValueById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/values/{id}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(valueService, times(1)).findValueById(anyLong());
    }

    @Test
    void whenUserAddsProject_thenReturns204() throws Exception {

        doReturn(12L).when(valueService).addValue(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/values/{value}", "Funny")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("12"));
    }

    @Test
    void whenUserAddsProject_thenProjectServiceCallsAddUserOnce() throws Exception {

        doReturn(12L).when(valueService).addValue(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/values/{value}", "Funny")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("12"));

        verify(valueService, times(1)).addValue(any(String.class));
    }


}

