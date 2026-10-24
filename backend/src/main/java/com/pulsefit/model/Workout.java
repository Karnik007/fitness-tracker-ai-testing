package com.pulsefit.model;

public class Workout {
    private String id;
    private String title;
    private String activityType; // Running, Weightlifting, Cycling, Swimming, Yoga
    private int durationMinutes;
    private int caloriesBurned;
    private String intensity; // Low, Medium, High
    private String heartRateZone; // Can be null, triggering Bug #3
    private String date;

    public Workout() {}

    public Workout(String id, String title, String activityType, int durationMinutes, int caloriesBurned, String intensity, String heartRateZone, String date) {
        this.id = id;
        this.title = title;
        this.activityType = activityType;
        this.durationMinutes = durationMinutes;
        this.caloriesBurned = caloriesBurned;
        this.intensity = intensity;
        this.heartRateZone = heartRateZone;
        this.date = date;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    public int getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(int caloriesBurned) { this.caloriesBurned = caloriesBurned; }
    public String getIntensity() { return intensity; }
    public void setIntensity(String intensity) { this.intensity = intensity; }
    public String getHeartRateZone() { return heartRateZone; }
    public void setHeartRateZone(String heartRateZone) { this.heartRateZone = heartRateZone; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}