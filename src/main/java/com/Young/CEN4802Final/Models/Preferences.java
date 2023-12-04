package com.Young.CEN4802Final.Models;

import jakarta.persistence.*;

@Entity
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private float shortBreakReminderInterval;

    @Column(nullable = false)
    private float longBreakReminderInterval;

    @Column(nullable = false)
    private float waterReminderInterval;

    @Column(nullable = false)
    private float foodReminderInterval;

    //getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
    	return Name;
    }
    public void setName(String Name) {
    	this.Name = Name;
    }

    public float getShortBreakReminderInterval() {
    	return shortBreakReminderInterval;
    }
    public void setShortBreakReminderInterval(float shortBreakReminderInterval) {
    	this.shortBreakReminderInterval = shortBreakReminderInterval;
    }

    public float getLongBreakReminderInterval() {
    	return longBreakReminderInterval;
    }
    public void setLongBreakReminderInterval(float longBreakReminderInterval) {
    	this.longBreakReminderInterval = longBreakReminderInterval;
    }

    public float getWaterReminderInterval() {
    	return waterReminderInterval;
    }
    public void setWaterReminderInterval(float waterReminderInterval) {
    	this.waterReminderInterval = waterReminderInterval;
    }

    public float getFoodReminderInterval() {
    	return foodReminderInterval;
    }
    public void setFoodReminderInterval(float foodReminderInterval) {
    	this.foodReminderInterval = foodReminderInterval;
    }


}
