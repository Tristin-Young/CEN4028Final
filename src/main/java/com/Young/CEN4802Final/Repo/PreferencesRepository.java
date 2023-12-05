package com.Young.CEN4802Final.Repo;

import com.Young.CEN4802Final.Models.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

}
