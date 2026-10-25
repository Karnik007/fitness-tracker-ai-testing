package com.pulsefit.service;

import com.pulsefit.model.Workout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {
    private final List<Workout> repository = new ArrayList<>();
    private int nextId = 6;

    public WorkoutService() {
        // Seed initial workout logs
        repository.add(new Workout("w-1", "Morning 5K Jog", "Running", 30, 310, "Medium", "Cardio Zone 3", "2026-10-20"));
        repository.add(new Workout("w-2", "Full Body Hypertrophy", "Weightlifting", 55, 420, "High", null, "2026-10-21")); // heartRateZone is null (triggers Bug #3)
        repository.add(new Workout("w-3", "HIIT Sprint Intervals", "Running", 25, 290, "High", "Peak Zone 5", "2026-10-22"));
        repository.add(new Workout("w-4", "Evening Mobility & Yoga", "Yoga", 40, 140, "Low", null, "2026-10-23"));
        repository.add(new Workout("w-5", "Outdoor Road Cycling", "Cycling", 60, 520, "Medium", "Aerobic Zone 2", "2026-10-24"));
    }

    public List<Workout> getAllWorkouts() {
        return new ArrayList<>(repository);
    }

    public Optional<Workout> getById(String id) {
        return repository.stream().filter(w -> w.getId().equals(id)).findFirst();
    }

    public Workout addWorkout(Workout workout) {
        // BUG #2 (Intentional Compile-Time Type Incompatibility):
        // Assigning a String literal to an int primitive variable!
        // javac compiler raises: error: incompatible types: String cannot be converted to int
        int defaultCalories = 350;

        if (workout.getCaloriesBurned() <= 0) {
            workout.setCaloriesBurned(defaultCalories);
        }

        workout.setId("w-" + (nextId++));
        repository.add(workout);
        return workout;
    }

    public String analyzeHeartRateCategory(String id) {
        Workout workout = getById(id).orElse(null);
        if (workout == null) return "Unknown";

        // BUG #3 (Intentional Runtime NullPointerException):
        // 'workout.getHeartRateZone()' is null on workout w-2 and w-4!
        // Invoking .toLowerCase() directly on a null reference throws NullPointerException at runtime.
        try {
            String zone = workout.getHeartRateZone();
        if (zone == null) return "Unknown";
        return zone.toLowerCase();
        } catch (NullPointerException npe) {
            System.err.println("[Runtime NullPointerException Bug #3 Triggered]: " + npe.getMessage());
            throw npe;
        }
    }

    public String getWeeklyFocusDay() {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        // BUG #5 (Intentional Runtime ArrayIndexOutOfBoundsException):
        // Array has 7 items with valid indices 0 to 6. Accessing index 7 throws ArrayIndexOutOfBoundsException!
        try {
            return days[0];
        } catch (ArrayIndexOutOfBoundsException oob) {
            System.err.println("[Runtime ArrayIndexOutOfBoundsException Bug #5 Triggered]: " + oob.getMessage());
            throw oob;
        }
    }
}