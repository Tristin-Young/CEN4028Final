package com.Young.CEN4802Final;

import com.Young.CEN4802Final.Controllers.WaterController;
import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.Models.Water;
import com.Young.CEN4802Final.Repo.PreferencesRepository;
import com.Young.CEN4802Final.services.WaterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(WaterController.class)
class WaterControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WaterService waterService;

    @MockBean
    private PreferencesRepository preferencesRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    // Test for GET /water/{id}
    @Test
    void getWaterLog_ShouldReturnWaterLog() throws Exception {
        Long id = 1L;
        Water water = new Water();
        Preferences preferences = new Preferences();
        preferences.setId(1L);  // Assume this is how preferences_id is set
        water.setPreferences(preferences);
        when(waterService.findById(id)).thenReturn(Optional.of(water));

        mockMvc.perform(get("/water/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(water)));

        verify(waterService).findById(id);
    }

    // Test for GET /water/totalWater/{id}
    @Test
    void getTotalWaterForDay_ShouldReturnTotalWater() throws Exception {
        Long id = 1L;
        float totalWater = 100.0f;
        when(waterService.getTotalWaterForDay(id)).thenReturn(totalWater);

        mockMvc.perform(get("/water/totalWater/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(totalWater)));

        verify(waterService).getTotalWaterForDay(id);
    }
}

