/**
 * PulseFit — Vue 3 Frontend Automated Benchmark Suite
 * Evaluates whether the AI model has corrected the 5 frontend compile-time and runtime errors.
 */

const VueTestSuite = {
    results: [],

    assert(description, passed, details = "") {
        this.results.push({ description, passed: Boolean(passed), details });
    },

    async runAll() {
        this.results = [];

        // ----------------------------------------------------
        // Test 6: Compile-Time SyntaxError 'cosnt' in calculator.js
        // ----------------------------------------------------
        let hasCosntTypo = false;
        try {
            const code = await fetch('src/utils/calculator.js').then(r => r.text());
            hasCosntTypo = /cosnt\s+MET_RUNNING_FACTOR/.test(code);
        } catch (e) {
            hasCosntTypo = true;
        }
        this.assert(
            "Bug #6 (Compile-Time / SyntaxError): calculator.js uses valid 'const' instead of 'cosnt'",
            !hasCosntTypo,
            "SyntaxError: Found 'cosnt MET_RUNNING_FACTOR' in src/utils/calculator.js"
        );

        // ----------------------------------------------------
        // Test 7: Compile-Time Vue Template Unterminated Element in WorkoutItem.vue
        // ----------------------------------------------------
        let hasUnterminatedElement = false;
        try {
            const code = await fetch('src/components/WorkoutItem.vue').then(r => r.text());
            const openDivCount = (code.match(/<div/g) || []).length;
            const closeDivCount = (code.match(/<\/div>/g) || []).length;
            hasUnterminatedElement = openDivCount !== closeDivCount;
        } catch (e) {
            hasUnterminatedElement = true;
        }
        this.assert(
            "Bug #7 (Compile-Time / Vue SFC): WorkoutItem.vue has balanced closing tags (no VueCompilerError)",
            !hasUnterminatedElement,
            "VueCompilerError: Element is missing end tag in WorkoutItem.vue"
        );

        // ----------------------------------------------------
        // Test 8: Compile-Time / CSS Property Typo in styles.css
        // ----------------------------------------------------
        let hasCssTypo = false;
        try {
            const code = await fetch('src/styles.css').then(r => r.text());
            hasCssTypo = code.includes("backgroud-color");
        } catch (e) {
            hasCssTypo = true;
        }
        this.assert(
            "Bug #8 (Compile-Time / CSS): styles.css contains no 'backgroud-color' typo",
            !hasCssTypo,
            "CSS property typo: 'backgroud-color' instead of 'background-color' in src/styles.css"
        );

        // ----------------------------------------------------
        // Test 9: Runtime TypeError in WorkoutList.vue
        // ----------------------------------------------------
        let hasGuardedWorkoutsFilter = false;
        try {
            const code = await fetch('src/components/WorkoutList.vue').then(r => r.text());
            hasGuardedWorkoutsFilter = /workouts\s*&&/.test(code) || 
                                       /workouts\?\.filter/.test(code) || 
                                       /\(workouts\s*\|\|\s*\[\]\)/.test(code) ||
                                       /default:\s*\(\)\s*=>\s*\[\]/.test(code);
        } catch (e) {
            hasGuardedWorkoutsFilter = false;
        }
        this.assert(
            "Bug #9 (Runtime TypeError): WorkoutList safely guards against undefined workouts prop",
            hasGuardedWorkoutsFilter,
            "TypeError: Cannot read properties of undefined (reading 'filter') in WorkoutList.vue"
        );

        // ----------------------------------------------------
        // Test 10: Runtime ReferenceError in Navbar.vue
        // ----------------------------------------------------
        let hasUndeclaredTriggerSync = true;
        try {
            const code = await fetch('src/components/Navbar.vue').then(r => r.text());
            const callsSync = code.includes("triggerSyncWithCloud");
            const declaresSync = /function\s+triggerSyncWithCloud/.test(code) || /const\s+triggerSyncWithCloud\s*=/.test(code);
            hasUndeclaredTriggerSync = callsSync && !declaresSync;
        } catch (e) {
            hasUndeclaredTriggerSync = true;
        }
        this.assert(
            "Bug #10 (Runtime ReferenceError): Navbar declares triggerSyncWithCloud before invoking",
            !hasUndeclaredTriggerSync,
            "ReferenceError: triggerSyncWithCloud is not defined in Navbar.vue"
        );

        return this.results;
    }
};

window.VueTestSuite = VueTestSuite;