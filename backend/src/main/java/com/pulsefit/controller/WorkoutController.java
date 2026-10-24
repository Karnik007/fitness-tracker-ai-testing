package com.pulsefit.controller;

import com.pulsefit.model.Workout;
import com.pulsefit.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Workout> list = workoutService.getAllWorkouts();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", list);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Workout workout) {
        Workout created = workoutService.addWorkout(workout);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/parse-duration")
    public ResponseEntity<Map<String, Object>> parseRawDuration(@RequestBody Map<String, Object> payload) {
        // BUG #4 (Intentional Runtime NumberFormatException):
        // Attempts Integer.parseInt on raw input without handling alphanumeric strings (e.g. "45mins")
        // Throws: java.lang.NumberFormatException: For input string: "45mins"
        try {
            String raw = (String) payload.getOrDefault("rawMinutes", "invalid_string");
            int parsed = Integer.parseInt(raw);
            Map<String, Object> res = new HashMap<>();
            res.put("duration", parsed);
            return ResponseEntity.ok(res);
        } catch (NumberFormatException nfe) {
            System.err.println("[Runtime NumberFormatException Bug #4 Triggered]: " + nfe.getMessage());
            throw nfe;
        }
    }

    @GetMapping("/{id}/zone")
    public ResponseEntity<Map<String, Object>> getZone(@PathVariable String id) {
        String zone = workoutService.analyzeHeartRateCategory(id);
        Map<String, Object> res = new HashMap<>();
        res.put("zone", zone);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/focus-day")
    public ResponseEntity<Map<String, Object>> getFocusDay() {
        String day = workoutService.getWeeklyFocusDay();
        Map<String, Object> res = new HashMap<>();
        res.put("day", day);
        return ResponseEntity.ok(res);
    }
}