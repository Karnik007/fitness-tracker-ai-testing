<!--
  PulseFit — Workout List Component
  Intentional Runtime TypeError:
    - Bug #9: workouts.filter called on undefined when prop is omitted
-->
<template>
  <div class="workout-list-wrapper">
    <div class="list-header">
      <h3>Logged Sessions</h3>
      <span class="badge">{{ displayCount }} Completed</span>
    </div>

    <!-- BUG #9 (Intentional Runtime TypeError):
         'workouts' prop is not defaulted or guarded with optional chaining.
         If <WorkoutList /> is mounted with workouts={undefined},
         V8 throws: TypeError: Cannot read properties of undefined (reading 'filter')
    -->
    <div class="workout-items-container">
      <div 
        v-for="item in (workouts ? workouts.filter(w => w.durationMinutes > 0) : [])" 
        :key="item.id"
      >
        <WorkoutItem :workout="item" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import WorkoutItem from './WorkoutItem.vue'

const props = defineProps({
  workouts: {
    type: Array,
    // Intentionally omitted default to expose Bug #9 if called unprovided
  }
})

const displayCount = computed(() => {
  return props.workouts ? props.workouts.length : 0
})
</script>