package com.Young.CEN4802Final.Repo;

import com.Young.CEN4802Final.Models.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WaterRepository extends JpaRepository<Water, Long> {


    List<Water> findByPreferences_IdAndDateTimeBetween(Long preferences_id, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
