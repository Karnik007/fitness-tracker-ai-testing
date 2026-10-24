# PulseFit — AI Benchmark Ground Truth Key (Compile-Time & Runtime Errors)

This benchmark evaluates an AI code-repair system across **Java (Spring Boot)** and **Vue.js 3 (Composition API / Vite)**.
It contains **strictly Compile-Time SyntaxErrors and Runtime Exceptions** (`NullPointerException`, `NumberFormatException`, `ArrayIndexOutOfBoundsException`, `TypeError`, `ReferenceError`) with **zero logical errors**.

---

## Master Error Matrix

| ID | Stack | Classification | Target File | Exact Exception / Error |
|---|---|---|---|---|
| **#1** | Java | **Compile-Time** (SyntaxError) | `backend/src/main/java/com/pulsefit/util/WorkoutValidator.java` | `javac` error: `';' expected` at line 9 (missing semicolon in constant declaration) |
| **#2** | Java | **Compile-Time** (Type Incompatibility) | `backend/src/main/java/com/pulsefit/service/WorkoutService.java` | `javac` error: `incompatible types: String cannot be converted to int` |
| **#3** | Java | **Runtime** (`NullPointerException`) | `backend/src/main/java/com/pulsefit/service/WorkoutService.java` | `java.lang.NullPointerException` when calling `.toLowerCase()` on null `heartRateZone` |
| **#4** | Java | **Runtime** (`NumberFormatException`) | `backend/src/main/java/com/pulsefit/controller/WorkoutController.java` | `java.lang.NumberFormatException: For input string` on unhandled string parsing |
| **#5** | Java | **Runtime** (`ArrayIndexOutOfBoundsException`) | `backend/src/main/java/com/pulsefit/service/WorkoutService.java` | `java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 7` |
| **#6** | Vue / JS | **Compile-Time** (SyntaxError) | `frontend/src/utils/calculator.js` | `SyntaxError: Unexpected identifier 'MET_RUNNING_FACTOR'` (keyword typo `cosnt`) |
| **#7** | Vue / SFC | **Compile-Time** (Vue Template Error) | `frontend/src/components/WorkoutItem.vue` | `VueCompilerError: Element is missing end tag.` (unterminated `<div>` tag) |
| **#8** | Vue / CSS | **Compile-Time** (CSS Syntax) | `frontend/src/styles.css` | Invalid CSS property: `backgroud-color` instead of `background-color` in `.intensity-high` |
| **#9** | Vue / JS | **Runtime** (`TypeError`) | `frontend/src/components/WorkoutList.vue` | `TypeError: Cannot read properties of undefined (reading 'filter')` when prop is omitted |
| **#10**| Vue / JS | **Runtime** (`ReferenceError`) | `frontend/src/components/Navbar.vue` | `ReferenceError: triggerSyncWithCloud is not defined` (invoked without declaration in script) |

---

## Detailed Error Specifications & Solutions

### Bug #1: Missing Semicolon in Java Constant (Compile-Time)
- **File**: `backend/src/main/java/com/pulsefit/util/WorkoutValidator.java`
- **Error**: `javac: ';' expected`
- **Buggy Code**:
  ```java
  public static final int MAX_DURATION_MINUTES = 360
  ```
- **Correction**:
  ```java
  public static final int MAX_DURATION_MINUTES = 360;
  ```
- **Explanation**: In Java, every statement and field declaration must terminate with a semicolon `;`.

---

### Bug #2: Type Incompatibility String to int (Compile-Time)
- **File**: `backend/src/main/java/com/pulsefit/service/WorkoutService.java`
- **Error**: `incompatible types: java.lang.String cannot be converted to int`
- **Buggy Code**:
  ```java
  int defaultCalories = "350";
  ```
- **Correction**:
  ```java
  int defaultCalories = 350;
  ```
- **Explanation**: Java is statically typed. A string literal cannot be assigned to a primitive integer variable without conversion.

---

### Bug #3: Null Reference Method Invocation (Runtime NullPointerException)
- **File**: `backend/src/main/java/com/pulsefit/service/WorkoutService.java`
- **Error**: `java.lang.NullPointerException`
- **Buggy Code**:
  ```java
  return workout.getHeartRateZone().toLowerCase();
  ```
- **Correction**:
  ```java
  if (workout.getHeartRateZone() == null) return "normal";
  return workout.getHeartRateZone().toLowerCase();
  ```
- **Explanation**: Workouts `w-2` and `w-4` have a null `heartRateZone`. Calling methods on null dereferences a null pointer.

---

### Bug #4: Unhandled String in Integer Parsing (Runtime NumberFormatException)
- **File**: `backend/src/main/java/com/pulsefit/controller/WorkoutController.java`
- **Error**: `java.lang.NumberFormatException: For input string`
- **Buggy Code**:
  ```java
  int parsed = Integer.parseInt(raw);
  ```
- **Correction**:
  ```java
  int parsed;
  try {
      parsed = Integer.parseInt(raw);
  } catch (NumberFormatException e) {
      parsed = 30; // fallback default
  }
  ```
- **Explanation**: Passing non-numeric characters to `Integer.parseInt` without a try-catch block crashes the thread.

---

### Bug #5: Array Out of Bounds Index (Runtime ArrayIndexOutOfBoundsException)
- **File**: `backend/src/main/java/com/pulsefit/service/WorkoutService.java`
- **Error**: `java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 7`
- **Buggy Code**:
  ```java
  String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
  return days[7];
  ```
- **Correction**:
  ```java
  String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
  return days[6]; // or days[dayIndex % 7]
  ```
- **Explanation**: Java arrays are 0-indexed. An array of length 7 only has indices 0 through 6. Index 7 is out of bounds.

---

### Bug #6: Typo in `const` Declaration (Vue / JS Compile-Time SyntaxError)
- **File**: `frontend/src/utils/calculator.js`
- **Error**: `SyntaxError: Unexpected identifier 'MET_RUNNING_FACTOR'`
- **Buggy Code**:
  ```javascript
  cosnt MET_RUNNING_FACTOR = 9.8;
  ```
- **Correction**:
  ```javascript
  export const MET_RUNNING_FACTOR = 9.8;
  ```
- **Explanation**: Typo in keyword `cosnt` prevents JavaScript engines from parsing the file.

---

### Bug #7: Unterminated Element Tag (Vue SFC Compile-Time Error)
- **File**: `frontend/src/components/WorkoutItem.vue`
- **Error**: `VueCompilerError: Element is missing end tag.`
- **Buggy Code**:
  ```html
  <div class="card-left">
      <span class="activity-emoji">{{ getActivityEmoji(...) }}</span>
      <div>...</div>
  <!-- Missing closing </div> for .card-left -->
  <div class="card-right">
  ```
- **Correction**:
  ```html
  <div class="card-left">
      <span class="activity-emoji">{{ getActivityEmoji(...) }}</span>
      <div>...</div>
  </div>
  <div class="card-right">
  ```
- **Explanation**: Vue's template parser requires matching opening and closing HTML tags.

---

### Bug #8: Misspelled CSS Property (Vue Compile-Time / CSS Syntax Error)
- **File**: `frontend/src/styles.css`
- **Error**: Invalid property declaration
- **Buggy Code**:
  ```css
  .intensity-high {
      backgroud-color: rgba(239, 68, 68, 0.2);
  }
  ```
- **Correction**:
  ```css
  .intensity-high {
      background-color: rgba(239, 68, 68, 0.2);
  }
  ```
- **Explanation**: Missing 'n' in `backgroud-color` causes browser CSS engines to reject the property.

---

### Bug #9: Calling `.filter()` on Undefined (Vue Runtime TypeError)
- **File**: `frontend/src/components/WorkoutList.vue`
- **Error**: `TypeError: Cannot read properties of undefined (reading 'filter')`
- **Buggy Code**:
  ```html
  <div v-for="item in (workouts.filter(w => w.durationMinutes > 0))" :key="item.id">
  ```
- **Correction**:
  ```html
  <div v-for="item in ((workouts || []).filter(w => w.durationMinutes > 0))" :key="item.id">
  ```
- **Explanation**: If `workouts` prop is not passed or undefined, accessing `.filter` triggers a fatal `TypeError`.

---

### Bug #10: Undeclared Template Handler (Vue Runtime ReferenceError)
- **File**: `frontend/src/components/Navbar.vue`
- **Error**: `ReferenceError: triggerSyncWithCloud is not defined`
- **Buggy Code**:
  ```html
  <button class="btn btn-secondary" @click="triggerSyncWithCloud">
  ```
- **Correction**:
  ```javascript
  <script setup>
  function triggerSyncWithCloud() {
      console.log("Cloud sync initialized");
  }
  </script>
  ```
- **Explanation**: Template event `@click` references a function identifier that was never declared in `<script setup>`.