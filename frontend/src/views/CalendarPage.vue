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
  created () {
    // 나중에 다른 곳으로 옮기자
    /* axios.get('http://localhost:9999/schedule')
      .then(res => {
        console.log('axios로 데이터 불러오기 성공. data=', res)
        this.scheduleArray = res.data
      })
      .catch(err => {
        console.log(err)
      })
    */
  },
  methods: {
  }
}
</script>
