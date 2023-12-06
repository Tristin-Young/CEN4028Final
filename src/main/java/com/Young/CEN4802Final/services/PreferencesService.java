package com.Young.CEN4802Final.services;

import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.Repo.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PreferencesService {

    @Autowired
    private PreferencesRepository preferencesRepository;

    public Optional<Preferences> findById(Long id) {
        return preferencesRepository.findById(id);
    }

    public Preferences save(Preferences preferences) {
        preferences.setLastShortBreakReminder(LocalDateTime.now());
        preferences.setLastLongBreakReminder(LocalDateTime.now());
        preferences.setLastWaterReminder(LocalDateTime.now());
        preferences.setLastFoodReminder(LocalDateTime.now());
        return preferencesRepository.save(preferences);
    }

    public Optional<Preferences> updateLastShortBreak(Long id) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setLastShortBreakReminder(LocalDateTime.now());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Preferences> updateLastLongBreak(Long id) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setLastLongBreakReminder(LocalDateTime.now());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Preferences> updateLastWaterBreak(Long id) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setLastWaterReminder(LocalDateTime.now());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Preferences> updateLastFoodBreak(Long id) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setLastFoodReminder(LocalDateTime.now());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public List<Preferences> getAllUsers() {
        return preferencesRepository.findAll();
    }

    public Optional<Preferences> updateShortBreakPreferences(Long id, Preferences preferences) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setShortBreakReminderInterval(preferences.getShortBreakReminderInterval());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Preferences> updateLongBreakPreferences(Long id, Preferences preferences) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setLongBreakReminderInterval(preferences.getLongBreakReminderInterval());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Preferences> updateWaterBreakPreferences(Long id, Preferences preferences) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setWaterReminderInterval(preferences.getWaterReminderInterval());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Preferences> updateFoodBreakPreferences(Long id, Preferences preferences) {
        return preferencesRepository.findById(id).map(existingPreferences -> {
            existingPreferences.setFoodReminderInterval(preferences.getFoodReminderInterval());
            return preferencesRepository.save(existingPreferences);
        });
    }

    public Optional<Long> calculateTimeRemaining(Long id, String type) {
        LocalDateTime now = LocalDateTime.now();
        switch (type) {
            case "short" -> {
                LocalDateTime lastShortBreak = preferencesRepository.findById(id).get().getLastShortBreakReminder();
                //difference in time, in minutes
                float interval = preferencesRepository.findById(id).get().getShortBreakReminderInterval();
                float minutesPassed = Duration.between(lastShortBreak, now).toMinutes();
                long timeRemaining = (long) (interval - minutesPassed);
                return Optional.of(Math.max(timeRemaining, 0));
            }
            case "long" -> {
                LocalDateTime lastLongBreak = preferencesRepository.findById(id).get().getLastLongBreakReminder();
                float interval = preferencesRepository.findById(id).get().getLongBreakReminderInterval();
                float minutesPassed = Duration.between(lastLongBreak, now).toMinutes();
                long timeRemaining = (long) (interval - minutesPassed);
                return Optional.of(Math.max(timeRemaining, 0));
            }
            case "water" -> {
                LocalDateTime lastWaterBreak = preferencesRepository.findById(id).get().getLastWaterReminder();
                float interval = preferencesRepository.findById(id).get().getWaterReminderInterval();
                float minutesPassed = Duration.between(lastWaterBreak, now).toMinutes();
                long timeRemaining = (long) (interval - minutesPassed);
                return Optional.of(Math.max(timeRemaining, 0));
            }
            case "food" -> {
                LocalDateTime lastFoodBreak = preferencesRepository.findById(id).get().getLastFoodReminder();
                float interval = preferencesRepository.findById(id).get().getFoodReminderInterval();
                float minutesPassed = Duration.between(lastFoodBreak, now).toMinutes();
                long timeRemaining = (long) (interval - minutesPassed);
                return Optional.of(Math.max(timeRemaining, 0));
            }
        }


        return Optional.of((long) 0);

    }
}
