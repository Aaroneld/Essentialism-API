package com.merrifield.Essentialism.API.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merrifield.Essentialism.API.config.SecurityConfig;
import com.merrifield.Essentialism.API.controllers.ProjectController;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValueId;
import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import com.merrifield.Essentialism.API.models.ProjectModels.ProjectMinimum;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.services.ProjectsService;
import com.merrifield.Essentialism.API.services.impl.SecurityUserServiceImpl;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.security.auth.message.config.AuthConfig;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProjectController.class, SecurityConfig.class, SecurityUserServiceImpl.class, AuthConfig.class})
public class ProjectControllersUnitTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProjectsService projectsService;

    @MockBean
    SecurityUserServiceImpl securityUserService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenUserRoleGetsAllProjects_thenReturns200() throws Exception {

        ProjectMinimum project1 = new ProjectMinimum("title1", "Description1", 1L);
        ProjectMinimum project2 = new ProjectMinimum("title2", "Description2", 2L);

        doReturn(List.of(project1, project2)).when(projectsService).findAllProjects();

        mockMvc.perform(MockMvcRequestBuilders.get("/projects")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(project1, project2))));

    }

    @Test
    void whenUserRoleGetsAllProjects_thenProjectServiceCallsFindAllProjectsOnce() throws Exception {

        ProjectMinimum project1 = new ProjectMinimum("title1", "Description1", 1L);
        ProjectMinimum project2 = new ProjectMinimum("title2", "Description2", 2L);

        doReturn(List.of(project1, project2)).when(projectsService).findAllProjects();

        mockMvc.perform(MockMvcRequestBuilders.get("/projects")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(projectsService, times(1)).findAllProjects();
    }

    @Test
    void whenUserRoleFindProjectByExistingId_thenReturns200() throws Exception {

        Project project = new Project(
                "title",
                "description",
                new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000"));
        project.setId(1);

        doReturn(project).when(projectsService).findProjectById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/projects/{id}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(project)));

    }


    @Test
    void whenUserRoleFindProjectByExistingId_thenProjectServiceCallsFindProjectByIdOnce() throws Exception {

        Project project = new Project(
                "title",
                "description",
                new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000"));
        project.setId(1);

        doReturn(project).when(projectsService).findProjectById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/projects/{id}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(projectsService, times(1)).findProjectById(anyLong());
    }

    @Test
    void whenUserRoleFindsProjectsByOwner_thenReturns200() throws Exception {

        Project project1 = new Project(
                "title2",
                "description2",
                new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000"));
        Project project2 = new Project(
                "title2",
                "description2",
                new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000"));

        project1.setId(1);
        project1.getUser().setId(1);
        project2.setId(2);
        project2.getUser().setId(1);

        doReturn(List.of(project1, project2)).when(projectsService).findProjectsOwnedByUser(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/projects/user/{userid}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(project1, project2))));

    }

    @Test
    void whenUserRoleFindsProjectsByOwner_thenProjectServiceCallsFindProjectsOwnedByUserOnce() throws Exception {

        Project project1 = new Project(
                "title2",
                "description2",
                new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000"));
        Project project2 = new Project(
                "title2",
                "description2",
                new User("username", "password", "firstName", "lastName", "email@email.com", "000-0000-000"));

        project1.setId(1);
        project1.getUser().setId(1);
        project2.setId(2);
        project2.getUser().setId(1);

        doReturn(List.of(project1, project2)).when(projectsService).findProjectsOwnedByUser(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/projects/user/{userid}", 1)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(projectsService, times(1)).findProjectsOwnedByUser(anyLong());
    }

    @Test
    void whenUserAddsProject_thenReturns204() throws Exception {

        ProjectMinimum project = new ProjectMinimum("title1", "Description1", 1L);

        doReturn(12L).when(projectsService).addProject(any(ProjectMinimum.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/projects", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(project)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("12"));
    }

    @Test
    void whenUserAddsProject_thenProjectServiceCallsAddUserOnce() throws Exception {

        ProjectMinimum project = new ProjectMinimum("title1", "Description1", 1L);

        doReturn(12L).when(projectsService).addProject(any(ProjectMinimum.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/projects", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(project)))
                .andDo(MockMvcResultHandlers.print());

        verify(projectsService, times(1)).addProject(any(ProjectMinimum.class));
    }

    @Test
    void whenUserAttemptsToAddAProjectResourceWithIncorrectOrMissingJSONParameters_thenReturns400() throws Exception {

        ProjectMinimum project = new ProjectMinimum(null, "Description1", 1L);

        doReturn(12L).when(projectsService).addProject(any(ProjectMinimum.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/projects", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(project)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void whenUserAddsProjectValueCorrectly_then201() throws Exception {

        ProjectValueId projectValueId = new ProjectValueId(1,2);

        doReturn(Set.of("Acceptance", "Compassion")).when(projectsService).addProjectValue(any(ProjectValueId.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectValueId)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(Set.of("Acceptance", "Compassion"))));
    }

    @Test
    void whenUserAddsProjectValue_thenProjectServiceCallsAddProjectValueOnce() throws Exception {

        ProjectValueId projectValueId = new ProjectValueId(1,2);

        doReturn(Set.of("Acceptance", "Compassion")).when(projectsService).addProjectValue(any(ProjectValueId.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectValueId)));

        verify(projectsService, times(1)).addProjectValue(any(ProjectValueId.class));
    }

    @Test
    void whenUserAttemptsToAddNewProjectValueResourceWithIncorrectOrMissingJSONParameters_thenReturns400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content("{'project': 1}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void whenUserDeletesProjectCorrectly_thenReturns204() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("Success"));
    }

    @Test
    void whenUserDeletesProject_thenProjectServiceCallsDeleteProjectOnce() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/{id}", 12L)
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json"));

        verify(projectsService, times(1)).deleteProject(anyLong());
    }


    @Test
    void whenUserDeletesProjectValueCorrectly_thenReturns204() throws Exception {

        ProjectValueId projectValueId = new ProjectValueId(1,2);

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectValueId)))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("Success"));
    }

    @Test
    void whenUserDeletesProjectValue_thenProjectServiceCallsDeleteProjectValueOnce() throws Exception {

        ProjectValueId projectValueId = new ProjectValueId(1,2);

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectValueId)));

        verify(projectsService, times(1)).deleteProjectValue(any(ProjectValueId.class));
    }

    @Test
    void whenUserAttemptsToDeleteProjectValueResourceWithIncorrectOrMissingJSONParameters_thenReturns400() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/value")
                .with(user("user").roles("USER"))
                .with(csrf())
                .contentType("application/json")
                .content("{'project': 1}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }
}
