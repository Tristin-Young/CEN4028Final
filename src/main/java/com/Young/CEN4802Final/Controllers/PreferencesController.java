package com.Young.CEN4802Final.Controllers;

import com.Young.CEN4802Final.Models.Preferences;
import com.Young.CEN4802Final.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preferences")
public class PreferencesController {

    @Autowired
    private PreferencesService preferencesService;

    @GetMapping("/{id}")
    public ResponseEntity<Preferences> getPreferences(@PathVariable Long id) {
        return preferencesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Preferences createPreferences(@RequestBody Preferences preferences) {
        return preferencesService.save(preferences);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Preferences> updatePreferences(@PathVariable Long id, @RequestBody Preferences preferences) {
//        return preferencesService.updatePreferences(id, preferences)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Preferences>> getAllUsers() {
        List<Preferences> users = preferencesService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/lastShortBreak/{id}")
    public ResponseEntity<Optional<Preferences>> getLastShortBreak(@PathVariable Long id){
        Optional<Preferences> lastShortBreak = preferencesService.updateLastShortBreak(id);
        return ResponseEntity.ok(lastShortBreak);
    }

    @PutMapping("/lastLongBreak/{id}")
    public ResponseEntity<Optional<Preferences>> getLastLongBreak(@PathVariable Long id){
        Optional<Preferences> lastLongBreak = preferencesService.updateLastLongBreak(id);
        return ResponseEntity.ok(lastLongBreak);
    }

    @PutMapping("/lastWaterBreak/{id}")
    public ResponseEntity<Optional<Preferences>> getLastWaterBreak(@PathVariable Long id){
        Optional<Preferences> lastWaterBreak = preferencesService.updateLastWaterBreak(id);
        return ResponseEntity.ok(lastWaterBreak);
    }

    @PutMapping("/lastFoodBreak/{id}")
    public ResponseEntity<Optional<Preferences>> getLastFoodBreak(@PathVariable Long id){
        Optional<Preferences> lastFoodBreak = preferencesService.updateLastFoodBreak(id);
        return ResponseEntity.ok(lastFoodBreak);
    }

    @PutMapping("/setShortBreak/{id}")
    public ResponseEntity<Optional<Preferences>> setShortBreak(@PathVariable Long id, @RequestBody Preferences preferences){
        Optional<Preferences> setShortBreak = preferencesService.updateShortBreakPreferences(id, preferences);
        return ResponseEntity.ok(setShortBreak);
    }

    @PutMapping("/setLongBreak/{id}")
    public ResponseEntity<Optional<Preferences>> setLongBreak(@PathVariable Long id, @RequestBody Preferences preferences){
        Optional<Preferences> setLongBreak = preferencesService.updateLongBreakPreferences(id, preferences);
        return ResponseEntity.ok(setLongBreak);
    }

    @PutMapping("/setWaterBreak/{id}")
    public ResponseEntity<Optional<Preferences>> setWaterBreak(@PathVariable Long id, @RequestBody Preferences preferences){
        Optional<Preferences> setWaterBreak = preferencesService.updateWaterBreakPreferences(id, preferences);
        return ResponseEntity.ok(setWaterBreak);
    }

    @PutMapping("/setFoodBreak/{id}")
    public ResponseEntity<Optional<Preferences>> setFoodBreak(@PathVariable Long id, @RequestBody Preferences preferences){
        Optional<Preferences> setFoodBreak = preferencesService.updateFoodBreakPreferences(id, preferences);
        return ResponseEntity.ok(setFoodBreak);
    }

    // In your PreferencesController (or a relevant controller)

    @GetMapping("/timeRemaining/{userId}/{type}")
    public ResponseEntity<Optional<Long>> getTimeRemaining(@PathVariable Long userId, @PathVariable String type) {
        // Logic to calculate time remaining for the specified type of reminder
        Optional<Long> timeRemaining = preferencesService.calculateTimeRemaining(userId, type);
        return ResponseEntity.ok(timeRemaining);
    }

}
