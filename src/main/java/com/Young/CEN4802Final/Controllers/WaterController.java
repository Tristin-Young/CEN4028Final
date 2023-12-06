package com.Young.CEN4802Final.Controllers;

import com.Young.CEN4802Final.Models.Water;
import com.Young.CEN4802Final.services.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/water")
public class WaterController {

    @Autowired
    private WaterService waterService;

    @GetMapping("/{id}")
    public ResponseEntity<Water> getWaterLog(@PathVariable Long id) {
        return waterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{preferencesId}")
    public ResponseEntity<Water> createWaterLog(@PathVariable Long preferencesId, @RequestBody Water water) {
        Water savedWater = waterService.save(preferencesId, water);
        return ResponseEntity.ok(savedWater);
    }

    @GetMapping("/totalWater/{id}")
    public ResponseEntity<Float> getTotalWaterForDay(@PathVariable Long id) {
        float total = waterService.getTotalWaterForDay(id);
        return ResponseEntity.ok(total);

    }
}
