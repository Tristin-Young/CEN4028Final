package com.Young.CEN4802Final.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Water {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "preferences_id" , nullable = false)
    private Preferences preferences;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private float waterAmount;

    //getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Preferences getPreferences() {
    	return preferences;
    }
    public void setPreferences(Preferences preferences) {
    	this.preferences = preferences;
    }

    public LocalDateTime getDateTime() {
    	return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
    	this.dateTime = dateTime;
    }

    public float getWaterAmount() {
    	return waterAmount;
    }
    public void setWaterAmount(float waterAmount) {
    	this.waterAmount = waterAmount;
    }

}
