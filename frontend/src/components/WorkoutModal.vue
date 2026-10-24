<template>
  <div v-if="isOpen" class="modal-backdrop">
    <div class="modal-card glass-panel">
      <div class="modal-header">
        <h3>Log Training Session</h3>
        <button class="close-x" @click="$emit('close')">&times;</button>
      </div>

      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label>Session Title</label>
          <input v-model="form.title" type="text" required placeholder="e.g. 10K Tempo Run" />
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>Activity</label>
            <select v-model="form.activityType">
              <option value="Running">Running</option>
              <option value="Weightlifting">Weightlifting</option>
              <option value="Cycling">Cycling</option>
              <option value="Yoga">Yoga</option>
              <option value="Swimming">Swimming</option>
            </select>
          </div>

          <div class="form-group">
            <label>Duration (mins)</label>
            <input v-model.number="form.durationMinutes" type="number" required min="5" max="300" />
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>Calories Burned</label>
            <input v-model.number="form.caloriesBurned" type="number" required min="10" />
          </div>

          <div class="form-group">
            <label>Intensity</label>
            <select v-model="form.intensity">
              <option value="Low">Low</option>
              <option value="Medium">Medium</option>
              <option value="High">High</option>
            </select>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" @click="$emit('close')">Cancel</button>
          <button type="submit" class="btn btn-primary">Save Session</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

defineProps({
  isOpen: Boolean
})

const emit = defineEmits(['close', 'add-workout'])

const form = reactive({
  title: '',
  activityType: 'Running',
  durationMinutes: 30,
  caloriesBurned: 280,
  intensity: 'Medium',
  date: new Date().toISOString().split('T')[0]
})

function submitForm() {
  emit('add-workout', { ...form, id: 'w-' + Date.now() })
  form.title = ''
  emit('close')
}
</script>