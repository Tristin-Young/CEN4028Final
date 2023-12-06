package com.Young.CEN4802Final;

import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.Models.Water;
import com.Young.CEN4802Final.Repo.PreferencesRepository;
import com.Young.CEN4802Final.Repo.WaterRepository;
import com.Young.CEN4802Final.services.WaterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaterServiceTest {

    @Mock
    private WaterRepository waterRepository;

    @Mock
    private PreferencesRepository preferencesRepository;

    @InjectMocks
    private WaterService waterService;

    @Test
    public void findById_ShouldReturnWater_WhenIdExists() {
        Long id = 1L;
        Water expectedWater = new Water();
        when(waterRepository.findById(id)).thenReturn(Optional.of(expectedWater));

        Optional<Water> result = waterService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedWater, result.get());
        verify(waterRepository).findById(id);
    }

    @Test
    public void save_ShouldSaveAndReturnWater() {
        Long preferencesId = 1L;
        Water water = new Water();
        Preferences mockPreferences = new Preferences(); // Create a mock Preferences object

        when(preferencesRepository.findById(preferencesId)).thenReturn(Optional.of(mockPreferences));
        when(waterRepository.save(water)).thenReturn(water);

        Water result = waterService.save(preferencesId, water);

        assertEquals(water, result);
        verify(waterRepository).save(water);
        verify(preferencesRepository).findById(preferencesId); // Verify that findById was called
    }

    @Test
    void totalWaterForDay_ShouldReturnFloat() {
        Long preferencesId = 1L;
        LocalDate today = LocalDate.now();
        List<Water> watersForToday = Arrays.asList(
                createWaterWithAmount(200.0f, today.atStartOfDay()),
                createWaterWithAmount(300.0f, today.atTime(12, 0))
        );

        when(waterRepository.findByPreferences_IdAndDateTimeBetween(
                eq(preferencesId),
                any(LocalDateTime.class),
                any(LocalDateTime.class))
        ).thenReturn(watersForToday);

        float totalWater = waterService.getTotalWaterForDay(preferencesId);

        assertEquals(500.0f, totalWater);
        verify(waterRepository).findByPreferences_IdAndDateTimeBetween(
                eq(preferencesId),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        );
    }

    private Water createWaterWithAmount(float amount, LocalDateTime dateTime) {
        Water water = new Water();
        water.setWaterAmount(amount);
        water.setDateTime(dateTime);
        return water;
    }
}
