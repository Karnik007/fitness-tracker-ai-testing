<template>
  <div class="app-container">
    <Navbar @open-modal="isModalOpen = true" />

    <section class="metrics-grid">
      <div class="stat-box glass-panel">
        <span class="stat-emoji">🔥</span>
        <div>
          <div class="stat-val">{{ totalCalories }} kcal</div>
          <div class="stat-lbl">Total Burned</div>
        </div>
      </div>

      <div class="stat-box glass-panel">
        <span class="stat-emoji">⏱️</span>
        <div>
          <div class="stat-val">{{ totalMinutes }} mins</div>
          <div class="stat-lbl">Active Time</div>
        </div>
      </div>

      <div class="stat-box glass-panel">
        <span class="stat-emoji">🏆</span>
        <div>
          <div class="stat-val">{{ workouts.length }}</div>
          <div class="stat-lbl">Completed Workouts</div>
        </div>
      </div>

      <div class="stat-box glass-panel">
        <span class="stat-emoji">⚡</span>
        <div>
          <div class="stat-val">{{ avgIntensity }}</div>
          <div class="stat-lbl">Dominant Intensity</div>
        </div>
      </div>
    </section>

    <WorkoutList :workouts="workouts" />

    <WorkoutModal 
      :is-open="isModalOpen" 
      @close="isModalOpen = false" 
      @add-workout="handleNewWorkout"
    />

    <footer class="footer">
      <p>PulseFit Vue 3 &amp; Java Spring Boot AI Benchmark &copy; 2026. Built for AI Automated Code Correction &amp; Debugging Evaluations.</p>
    </footer>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import Navbar from './components/Navbar.vue'
import WorkoutList from './components/WorkoutList.vue'
import WorkoutModal from './components/WorkoutModal.vue'

const isModalOpen = ref(false)

const workouts = ref([
  { id: 'w-1', title: 'Morning 5K Jog', activityType: 'Running', durationMinutes: 30, caloriesBurned: 310, intensity: 'Medium', date: '2026-10-20' },
  { id: 'w-2', title: 'Full Body Hypertrophy', activityType: 'Weightlifting', durationMinutes: 55, caloriesBurned: 420, intensity: 'High', date: '2026-10-21' },
  { id: 'w-3', title: 'HIIT Sprint Intervals', activityType: 'Running', durationMinutes: 25, caloriesBurned: 290, intensity: 'High', date: '2026-10-22' },
  { id: 'w-4', title: 'Evening Mobility & Yoga', activityType: 'Yoga', durationMinutes: 40, caloriesBurned: 140, intensity: 'Low', date: '2026-10-23' },
  { id: 'w-5', title: 'Outdoor Road Cycling', activityType: 'Cycling', durationMinutes: 60, caloriesBurned: 520, intensity: 'Medium', date: '2026-10-24' }
])

onMounted(async () => {
  try {
    const res = await fetch('http://localhost:8080/api/workouts')
    const json = await res.json()
    if (json.success && Array.isArray(json.data)) {
      workouts.value = json.data
    }
  } catch (err) {
    console.log('Backend offline, using mock store')
  }
})

const totalCalories = computed(() => workouts.value.reduce((s, w) => s + w.caloriesBurned, 0))
const totalMinutes = computed(() => workouts.value.reduce((s, w) => s + w.durationMinutes, 0))
const avgIntensity = computed(() => {
  if (workouts.value.length === 0) return 'N/A'
  const counts = {}
  workouts.value.forEach(w => {
    counts[w.intensity] = (counts[w.intensity] || 0) + 1
  })
  return Object.keys(counts).reduce((a, b) => counts[a] > counts[b] ? a : b)
})

function handleNewWorkout(item) {
  workouts.value.unshift(item)
}
</script>