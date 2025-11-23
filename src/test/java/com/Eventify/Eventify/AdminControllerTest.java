package com.Eventify.Eventify;

import com.Eventify.Eventify.Entity.Role;
import com.Eventify.Eventify.Entity.User;
import com.Eventify.Eventify.config.TestSecurityConfig;
import com.Eventify.Eventify.controller.AdminController;
import com.Eventify.Eventify.service.EventService;
import com.Eventify.Eventify.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private EventService eventService;

//test de profile easy
    @Test
    void shouldReturnAllUsers() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setName("Hamza");
        user.setEmail("hamza@gmail.com");

        Mockito.when(userService.all()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Hamza"))
                .andExpect(jsonPath("$[0].email").value("hamza@gmail.com"));
    }


    @Test
    void shouldUpdateRole() throws Exception {

        User updatedUser = new User();
        updatedUser.setId(5L);
        updatedUser.setName("TestUser");
        updatedUser.setEmail("test@gmail.com");

        Role newRole = new Role();
        newRole.setName("ADMIN");
        updatedUser.setRole(newRole);

        Mockito.when(userService.updateRole(anyLong(), anyString()))
                .thenReturn(updatedUser);

        mockMvc.perform(put("/api/admin/users/5/role/ADMIN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.role.name").value("ADMIN"));
    }



    @Test
    void shouldDeleteEvent() throws Exception {

        Mockito.doNothing().when(eventService).deleteEvent(10L);


        mockMvc.perform(delete("/api/admin/events/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adminDeleted").value(true));
    }
}
