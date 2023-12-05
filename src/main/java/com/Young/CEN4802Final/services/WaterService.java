package com.Young.CEN4802Final.services;

import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.Models.Water;
import com.Young.CEN4802Final.Repo.PreferencesRepository;
import com.Young.CEN4802Final.Repo.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WaterService {

    @Autowired
    private WaterRepository waterRepository;

    @Autowired
    private PreferencesRepository preferencesRepository;

    public Optional<Water> findById(Long id) {
        return waterRepository.findById(id);
    }

    public Water save(Long preferencesId, Water water) {
        Preferences preferences = preferencesRepository.findById(preferencesId)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));

        water.setPreferences(preferences); // Set the preferences
        water.setDateTime(LocalDateTime.now()); // Set the current date and time
        return waterRepository.save(water);
    }

    public Optional<Water> update(Long id, Water water) {
        return waterRepository.findById(id).map(existingWaterLog -> {
            // Update the necessary fields from water
            // For example:
            // existingWaterLog.setWaterAmount(water.getWaterAmount());
            // Add similar lines for other fields
            return waterRepository.save(existingWaterLog);
        });
    }

    //get total water for the day for a specific user
    public float getTotalWaterForDay(Long preferences_id) {

        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        List<Water> todaysWater = waterRepository.findByPreferences_IdAndDateTimeBetween(preferences_id, startOfDay, endOfDay);
        float total = 0;
        for(Water water : todaysWater) {
            total += water.getWaterAmount();
        }
        return total;
    }
}
