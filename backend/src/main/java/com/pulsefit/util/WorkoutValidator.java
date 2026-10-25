package com.pulsefit.util;

import com.pulsefit.model.Workout;

public class WorkoutValidator {
    // BUG #1 (Intentional Compile-Time SyntaxError):
    // Missing semicolon ';' at the end of the constant declaration!
    // javac compiler raises: error: ';' expected
    public static final int MAX_DURATION_MINUTES = 360;

    public static boolean isValidWorkout(Workout workout) {
        if (workout == null) return false;
        if (workout.getTitle() == null || workout.getTitle().trim().length() < 2) return false;
        if (workout.getDurationMinutes() <= 0 || workout.getDurationMinutes() > MAX_DURATION_MINUTES) return false;
        return true;
    }
}