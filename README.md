# 🔥 PulseFit — Fullstack Vue 3 & Java Spring Boot AI Benchmark Suite

> **A high-performance fitness & workout activity platform built with a Vue 3 frontend and a Java 21 Spring Boot REST backend, featuring 10 deliberate compile-time and runtime errors for benchmarking AI automated code-correction systems.**

[![GitHub Repo](https://img.shields.io/badge/GitHub-Repository-blue?logo=github)](https://github.com/Karnik007/fitness-tracker-ai-testing)
[![Frontend](https://img.shields.io/badge/Frontend-Vue.js%203%20%7C%20Vite-42b883?logo=vuedotjs)](https://github.com/Karnik007/fitness-tracker-ai-testing)
[![Backend](https://img.shields.io/badge/Backend-Java%2021%20%7C%20Spring%20Boot-6db33f?logo=springboot)](https://github.com/Karnik007/fitness-tracker-ai-testing)
[![AI Benchmark](https://img.shields.io/badge/AI%20Benchmark-Strict%20Compile%20%26%20Runtime%20Errors-purple)](#error-manifest-summary)

---

## 🎯 Benchmark Objective

PulseFit benchmarks automated AI repair pipelines against **strict language parser and compiler diagnostics**:
- **Compile-Time & Syntax Errors**: `javac` compile errors, missing semicolons, static type incompatibilities, Vue Single File Component template syntax errors, and CSS property typos.
- **Runtime Exceptions**: `NullPointerException`, `NumberFormatException`, `ArrayIndexOutOfBoundsException`, `TypeError`, and `ReferenceError`.
- **Automated Verification**: Java tests with JUnit (`backend/src/test/java/com/pulsefit/WorkoutServiceTest.java`) and browser-based Vue test assertions (`frontend/frontend_tests.html`).

---

## 📁 Repository Structure

```
fitness-tracker-ai-testing/
├── backend/                                           # Java 21 Spring Boot REST API
│   ├── pom.xml                                        # Maven configuration
│   └── src/
│       ├── main/java/com/pulsefit/
│       │   ├── PulseFitApplication.java               # Spring Boot entry point
│       │   ├── controller/WorkoutController.java      # REST Controller (Contains Runtime Error #4)
│       │   ├── service/WorkoutService.java            # Business logic (Contains Errors #2, #3, #5)
│       │   ├── model/Workout.java                     # Entity model
│       │   └── util/WorkoutValidator.java             # Input validation (Contains Compile Error #1)
│       └── test/java/com/pulsefit/
│           └── WorkoutServiceTest.java                # Automated JUnit test suite
│
├── frontend/                                          # Vue.js 3 Application (Vite)
│   ├── package.json                                   # Vue 3, Vite dependencies
│   ├── vite.config.js                                 # Vite configuration
│   ├── index.html                                     # Entry HTML host
│   ├── frontend_tests.html                            # In-browser test runner
│   ├── frontend_tests.js                              # Vue evaluation assertions
│   └── src/
│       ├── main.js                                    # Vue root bootstrap
│       ├── App.vue                                    # Main Vue dashboard component
│       ├── styles.css                                 # Stylesheet (Contains CSS Error #8)
│       ├── components/
│       │   ├── Navbar.vue                             # Contains Runtime Error #10
│       │   ├── WorkoutList.vue                        # Contains Runtime Error #9
│       │   ├── WorkoutItem.vue                        # Contains Compile Template Error #7
│       │   └── WorkoutModal.vue
│       └── utils/
│           └── calculator.js                          # Contains Compile Error #6
│
├── requirements.txt                                   # Master environment specifications
├── bugs.md                                            # Ground Truth Master Solution Key
└── README.md                                          # Project documentation
```

---

## 🚀 How to Run

### 1. Backend (Java Spring Boot)
Requires Java 21+ and Maven:
```bash
cd backend
mvn clean spring-boot:run
```
The REST API starts at `http://localhost:8080/api/workouts`.

To test backend compilation:
```bash
javac src/main/java/com/pulsefit/util/WorkoutValidator.java
```

### 2. Frontend (Vue.js 3)
```bash
cd frontend
npm install
npm run dev
```
Or open `frontend/frontend_tests.html` directly in any web browser to run the client-side error assertions.

---

## 🐞 Error Manifest Summary

| ID | Stack | Type | Target File | Description |
|---|---|---|---|---|
| **1** | Java | SyntaxError | `util/WorkoutValidator.java` | Missing semicolon `;` in constant declaration |
| **2** | Java | Type Mismatch | `service/WorkoutService.java` | `int defaultCalories = "350";` (String to int) |
| **3** | Java | NullPointer | `service/WorkoutService.java` | Calling `.toLowerCase()` on null `heartRateZone` |
| **4** | Java | NumberFormat | `controller/WorkoutController.java` | Unhandled `Integer.parseInt` on raw string |
| **5** | Java | OutOfBounds | `service/WorkoutService.java` | Array index 7 accessed on 7-element array |
| **6** | Vue | SyntaxError | `utils/calculator.js` | Keyword typo `cosnt MET_RUNNING_FACTOR` |
| **7** | Vue | Template Error | `components/WorkoutItem.vue` | VueCompilerError: Element missing end tag |
| **8** | Vue | CSS Syntax | `styles.css` | Property typo `backgroud-color` |
| **9** | Vue | TypeError | `components/WorkoutList.vue` | Calling `.filter()` on `undefined` |
| **10**| Vue | ReferenceError | `components/Navbar.vue` | Undeclared function `triggerSyncWithCloud` |

Detailed solutions, AST traces, and diffs are cataloged in [bugs.md](bugs.md).

---

## 📄 License
MIT License. Created for AI code-repair and automated testing experiments.