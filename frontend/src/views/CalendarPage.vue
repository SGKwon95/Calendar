<template>
  <div class="calendarpage">
    <layout>
      <template #content>
        <calendar/>
      </template>
    </layout>
  </div>
</template>

<script>
// @ is an alias to /src
/* eslint-disable no-unused-vars */
import Vue from 'vue'
import Layout from '@/components/Layout'
import Calendar from '@/components/Calendar'

export default {
  name: 'CalendarPage',
  components: {
    Calendar,
    Layout
  }
}
</script>
