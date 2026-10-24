/**
 * PulseFit — Fitness Met & Calorie Calculation Helper
 * 
 * Intentional Compile-Time Syntax Error:
 *  - Bug #6: Typo 'cosnt' instead of 'const'
 */

// BUG #6 (Intentional Compile-Time Syntax Error):
// Typo in keyword 'cosnt' instead of 'const'.
// V8/Babel parser raises: SyntaxError: Unexpected identifier 'MET_RUNNING_FACTOR'
cosnt MET_RUNNING_FACTOR = 9.8;
export const MET_CYCLING_FACTOR = 7.5;
export const MET_LIFTING_FACTOR = 6.0;

export function estimateCalories(activity, minutes, bodyWeightKg = 70) {
    let met = 5.0;
    if (activity === 'Running') met = MET_RUNNING_FACTOR;
    else if (activity === 'Cycling') met = MET_CYCLING_FACTOR;
    else if (activity === 'Weightlifting') met = MET_LIFTING_FACTOR;

    return Math.round((met * 3.5 * bodyWeightKg / 200) * minutes);
}