package com.pulsefit;

import com.pulsefit.service.WorkoutService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutServiceTest {

    @Test
    public void testBug3_NullPointerException() {
        WorkoutService service = new WorkoutService();
        // w-2 has null heartRateZone, should throw NullPointerException until patched
        assertThrows(NullPointerException.class, () -> {
            service.analyzeHeartRateCategory("w-2");
        }, "Expected NullPointerException on null heartRateZone (Bug #3)");
    }

    @Test
    public void testBug5_ArrayIndexOutOfBoundsException() {
        WorkoutService service = new WorkoutService();
        // Index 7 on 7-element array throws ArrayIndexOutOfBoundsException
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            service.getWeeklyFocusDay();
        }, "Expected ArrayIndexOutOfBoundsException on out-of-bounds day index (Bug #5)");
    }
}