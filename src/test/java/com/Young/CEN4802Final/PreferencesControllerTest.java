package com.Young.CEN4802Final;

import com.Young.CEN4802Final.Controllers.PreferencesController;
import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(PreferencesController.class)
class PreferencesControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreferencesService preferencesService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    // Test for GET /preferences/{id}
    @Test
    void getPreferences_ShouldReturnPreferences_WhenIdExists() throws Exception {
        Long id = 1L;
        Preferences mockPreferences = new Preferences(); // Create a mock Preferences instance
        when(preferencesService.findById(id)).thenReturn(Optional.of(mockPreferences));

        mockMvc.perform(get("/preferences/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockPreferences)));

        verify(preferencesService).findById(id);
    }

    // Test for POST /preferences/
    @Test
    void createPreferences_ShouldCreateAndReturnPreferences() throws Exception {
        Preferences newPreferences = new Preferences();
        // Set properties for newPreferences as necessary
        newPreferences.setName("Test User");
        newPreferences.setShortBreakReminderInterval(15);
        newPreferences.setLongBreakReminderInterval(60);
        newPreferences.setWaterReminderInterval(20);
        newPreferences.setFoodReminderInterval(180);

        when(preferencesService.save(any(Preferences.class))).thenReturn(newPreferences);

        mockMvc.perform(post("/preferences/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPreferences)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newPreferences)));

        verify(preferencesService).save(any(Preferences.class));
    }

    // Test for GET /preferences/allUsers
    @Test
    void getAllUsers_ShouldReturnListOfAllUsers() throws Exception {
        List<Preferences> users = Arrays.asList(new Preferences(), new Preferences());
        when(preferencesService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/preferences/allUsers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));

        verify(preferencesService).getAllUsers();
    }

    @Test
    void updateLastShortBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences updatedPreferences = new Preferences(); // Create a mock Preferences instance
        when(preferencesService.updateLastShortBreak(id)).thenReturn(Optional.of(updatedPreferences));

        mockMvc.perform(put("/preferences/lastShortBreak/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedPreferences)));

        verify(preferencesService).updateLastShortBreak(id);
    }

    @Test
    void updateLastLongBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences updatedPreferences = new Preferences(); // Create a mock Preferences instance
        when(preferencesService.updateLastLongBreak(id)).thenReturn(Optional.of(updatedPreferences));

        mockMvc.perform(put("/preferences/lastLongBreak/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedPreferences)));

        verify(preferencesService).updateLastLongBreak(id);
    }

    @Test
    void updateLastWaterBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences updatedPreferences = new Preferences(); // Create a mock Preferences instance
        when(preferencesService.updateLastWaterBreak(id)).thenReturn(Optional.of(updatedPreferences));

        mockMvc.perform(put("/preferences/lastWaterBreak/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedPreferences)));

        verify(preferencesService).updateLastWaterBreak(id);
    }

    @Test
    void updateLastFoodBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences updatedPreferences = new Preferences(); // Create a mock Preferences instance
        when(preferencesService.updateLastFoodBreak(id)).thenReturn(Optional.of(updatedPreferences));

        mockMvc.perform(put("/preferences/lastFoodBreak/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedPreferences)));

        verify(preferencesService).updateLastFoodBreak(id);
    }

    @Test
    void setShortBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences preferences = new Preferences();
        preferences.setShortBreakReminderInterval(30);  // Example interval

        when(preferencesService.updateShortBreakPreferences(eq(id), any(Preferences.class)))
                .thenReturn(Optional.of(preferences));

        mockMvc.perform(put("/preferences/setShortBreak/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(preferences)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(preferences)));

        verify(preferencesService).updateShortBreakPreferences(eq(id), any(Preferences.class));
    }

    @Test
    void setLongBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences preferences = new Preferences();
        preferences.setLongBreakReminderInterval(60);  // Example interval

        when(preferencesService.updateLongBreakPreferences(eq(id), any(Preferences.class)))
                .thenReturn(Optional.of(preferences));

        mockMvc.perform(put("/preferences/setLongBreak/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(preferences)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(preferences)));

        verify(preferencesService).updateLongBreakPreferences(eq(id), any(Preferences.class));
    }

    @Test
    void setWaterBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences preferences = new Preferences();
        preferences.setWaterReminderInterval(60);  // Example interval

        when(preferencesService.updateWaterBreakPreferences(eq(id), any(Preferences.class)))
                .thenReturn(Optional.of(preferences));

        mockMvc.perform(put("/preferences/setWaterBreak/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(preferences)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(preferences)));

        verify(preferencesService).updateWaterBreakPreferences(eq(id), any(Preferences.class));
    }

    @Test
    void setFoodBreak_ShouldUpdateAndReturnPreferences() throws Exception {
        Long id = 1L;
        Preferences preferences = new Preferences();
        preferences.setFoodReminderInterval(60);  // Example interval

        when(preferencesService.updateFoodBreakPreferences(eq(id), any(Preferences.class)))
                .thenReturn(Optional.of(preferences));

        mockMvc.perform(put("/preferences/setFoodBreak/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(preferences)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(preferences)));

        verify(preferencesService).updateFoodBreakPreferences(eq(id), any(Preferences.class));
    }

    @Test
    void getTimeRemaining_ShouldReturnTimeRemaining() throws Exception {
        Long userId = 1L;
        String type = "short";
        Optional<Long> timeRemaining = Optional.of(15L);  // Example remaining time

        when(preferencesService.calculateTimeRemaining(userId, type)).thenReturn(timeRemaining);

        mockMvc.perform(get("/preferences/timeRemaining/" + userId + "/" + type))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(timeRemaining)));

        verify(preferencesService).calculateTimeRemaining(userId, type);
    }


}
