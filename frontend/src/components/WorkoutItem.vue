<!--
  PulseFit — Individual Workout Item Card (Vue 3 SFC)
  Intentional Compile-Time Template Syntax Error:
    - Bug #7: VueCompilerError: Element is missing end tag.
-->
<template>
  <div class="workout-card glass-panel">
    <div class="card-left">
      <span class="activity-emoji">{{ getActivityEmoji(workout.activityType) }}</span>
      <div>
        <h4 class="workout-title">{{ workout.title }}</h4>
        <span class="workout-sub">{{ workout.activityType }} &bull; {{ workout.date }}</span>
      </div>
    </div>
    <!-- BUG #7 (Intentional Compile-Time Vue Template Syntax Error):
         Missing closing </div> tag for .card-left!
         Vue template compiler raises: VueCompilerError: Element is missing end tag.
    -->
    <div class="card-right">
      <div class="stat-pill">
        <span class="val">{{ workout.durationMinutes }}m</span>
        <span class="lbl">Duration</span>
      </div>
      <div class="stat-pill burn">
        <span class="val">{{ workout.caloriesBurned }}</span>
        <span class="lbl">Kcal</span>
      </div>
      <span :class="['intensity-tag', 'intensity-' + workout.intensity.toLowerCase()]">
        {{ workout.intensity }}
      </span>
    </div>
  </div>
</template>

<script setup>
defineProps({
  workout: {
    type: Object,
    required: true
  }
})

function getActivityEmoji(type) {
  const map = {
    Running: '🏃',
    Weightlifting: '🏋️',
    Cycling: '🚴',
    Yoga: '🧘',
    Swimming: '🏊'
  }
  return map[type] || '⚡'
}
</script>