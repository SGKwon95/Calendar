<template>
  <div class="calendarpage">
    <layout/>
    <calendar :schedule-array="scheduleArray"/>
    <tr>
      <td v-for="i in scheduleArray" :key=i.id>{{ i }}</td>
    </tr>
  </div>
</template>

<script>
// @ is an alias to /src
/* eslint-disable no-unused-vars */
import Vue from 'vue'
import axios from 'axios'
import store from '../store'
import Layout from '@/components/Layout'
import Calendar from '@/components/Calendar'

export default {
  name: 'CalendarPage',
  components: {
    Calendar,
    Layout
  },
  data () {
    return {
      scheduleArray: []
    }
  },
  created () {
    axios.get('http://localhost:9999/schedule')
      .then(res => {
        console.log(res)
        this.scheduleArray = res.data
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
