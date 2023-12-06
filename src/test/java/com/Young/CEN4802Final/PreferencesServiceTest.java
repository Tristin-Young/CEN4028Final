package com.Young.CEN4802Final;

import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.Repo.PreferencesRepository;
import com.Young.CEN4802Final.services.PreferencesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PreferencesServiceTest {

    @Mock
    private PreferencesRepository preferencesRepository;

    @InjectMocks
    private PreferencesService preferencesService;

    @Test
    void findById_ShouldReturnPreferences_WhenIdExists() {
        Long id = 1L;
        Preferences expectedPreferences = new Preferences();
        when(preferencesRepository.findById(id)).thenReturn(Optional.of(expectedPreferences));

        Optional<Preferences> result = preferencesService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedPreferences, result.get());
        verify(preferencesRepository).findById(id);
    }

    @Test
    void save_ShouldSaveAndReturnPreferences() {
        Preferences preferences = new Preferences();
        when(preferencesRepository.save(preferences)).thenReturn(preferences);

        Preferences result = preferencesService.save(preferences);

        assertEquals(preferences, result);
        verify(preferencesRepository).save(preferences);
    }

    @Test
    void updateLastShortBreak_ShouldUpdateAndReturnPreferences_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Preferences> result = preferencesService.updateLastShortBreak(id);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getLastShortBreakReminder());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void updateLastLongBreak_ShouldUpdateAndReturnPreferences_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Preferences> result = preferencesService.updateLastLongBreak(id);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getLastLongBreakReminder());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void updateLastFoodBreak_ShouldUpdateAndReturnPreferences_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Preferences> result = preferencesService.updateLastFoodBreak(id);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getLastFoodReminder());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void updateLastWaterBreak_ShouldUpdateAndReturnPreferences_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Preferences> result = preferencesService.updateLastWaterBreak(id);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getLastWaterReminder());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void getAllUsers_ShouldReturnListOfPreferences() {
        List<Preferences> expectedList = Arrays.asList(new Preferences(), new Preferences());
        when(preferencesRepository.findAll()).thenReturn(expectedList);

        List<Preferences> result = preferencesService.getAllUsers();

        assertNotNull(result);
        assertEquals(expectedList.size(), result.size());
        assertEquals(expectedList, result);
        verify(preferencesRepository).findAll();
    }

    @Test
    void updateShortBreakPreferences_ShouldUpdateInterval_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        existingPreferences.setShortBreakReminderInterval(30);
        Preferences updatedPreferences = new Preferences();
        updatedPreferences.setShortBreakReminderInterval(45);

        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenReturn(updatedPreferences);

        Optional<Preferences> result = preferencesService.updateShortBreakPreferences(id, updatedPreferences);

        assertTrue(result.isPresent());
        assertEquals(45, result.get().getShortBreakReminderInterval());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void updateLongBreakPreferences_ShouldUpdateInterval_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        existingPreferences.setLongBreakReminderInterval(30);
        Preferences updatedPreferences = new Preferences();
        updatedPreferences.setLongBreakReminderInterval(45);

        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenReturn(updatedPreferences);

        Optional<Preferences> result = preferencesService.updateLongBreakPreferences(id, updatedPreferences);

        assertTrue(result.isPresent());
        assertEquals(45, result.get().getLongBreakReminderInterval());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void updateWaterBreakPreferences_ShouldUpdateInterval_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        existingPreferences.setWaterReminderInterval(30);
        Preferences updatedPreferences = new Preferences();
        updatedPreferences.setWaterReminderInterval(45);

        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenReturn(updatedPreferences);

        Optional<Preferences> result = preferencesService.updateWaterBreakPreferences(id, updatedPreferences);

        assertTrue(result.isPresent());
        assertEquals(45, result.get().getWaterReminderInterval());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }

    @Test
    void updateFoodBreakPreferences_ShouldUpdateInterval_WhenIdExists() {
        Long id = 1L;
        Preferences existingPreferences = new Preferences();
        existingPreferences.setFoodReminderInterval(30);
        Preferences updatedPreferences = new Preferences();
        updatedPreferences.setFoodReminderInterval(45);

        when(preferencesRepository.findById(id)).thenReturn(Optional.of(existingPreferences));
        when(preferencesRepository.save(any(Preferences.class))).thenReturn(updatedPreferences);

        Optional<Preferences> result = preferencesService.updateFoodBreakPreferences(id, updatedPreferences);

        assertTrue(result.isPresent());
        assertEquals(45, result.get().getFoodReminderInterval());
        verify(preferencesRepository).findById(id);
        verify(preferencesRepository).save(existingPreferences);
    }


    @Test
    void calculateTimeRemaining_ShouldReturnCorrectTimeRemaining() {
        Long id = 1L;
        String type = "short";
        Preferences preferences = new Preferences();
        preferences.setLastShortBreakReminder(LocalDateTime.now().minusMinutes(30));
        preferences.setShortBreakReminderInterval(60); // 1 hour interval
        when(preferencesRepository.findById(id)).thenReturn(Optional.of(preferences));

        Optional<Long> result = preferencesService.calculateTimeRemaining(id, type);

        assertTrue(result.isPresent());
        assertEquals(30, result.get()); // 30 minutes should be remaining
        verify(preferencesRepository, times(2)).findById(id);
    }


}

