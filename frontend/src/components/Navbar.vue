<!--
  PulseFit — Top Navigation Bar
  Intentional Runtime ReferenceError:
    - Bug #10: 'triggerSyncWithCloud' is called in template without declaration
-->
<template>
  <header class="app-header glass-panel">
    <div class="brand">
      <span class="brand-icon">🔥</span>
      <div>
        <h1 class="brand-title">Pulse<span>Fit</span></h1>
        <p class="brand-tagline">Vue 3 &amp; Spring Boot AI Benchmark</p>
      </div>
    </div>

    <div class="header-actions">
      <!-- BUG #10 (Intentional Runtime ReferenceError):
           Clicking this button invokes 'triggerSyncWithCloud', which is never declared in <script setup>!
           Vue runtime raises: ReferenceError: triggerSyncWithCloud is not defined
      -->
      function triggerSyncWithCloud() {
  // Implementation for cloud sync
}
        ☁ Cloud Sync
      </button>

      <button class="btn btn-primary" @click="$emit('open-modal')">
        + Log Workout
      </button>
    </div>
  </header>
</template>

<script setup>
defineEmits(['open-modal'])
// triggerSyncWithCloud is intentionally omitted to trigger Bug #10 on button click!
</script>