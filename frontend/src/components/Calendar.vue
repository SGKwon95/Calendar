<template>
  <div align="center">
      <h1>{{ thisYear }}년
      <button @click="prevMonth()" class="page-btn">
        ◀
      </button>
      &nbsp; &nbsp; <b>{{ thisMonth }}월</b> &nbsp; &nbsp;
      <button @click="nextMonth()" class="page-btn">
         ▶
      </button> &emsp;
      <v-btn rounded color="teal accent-3" dark @click="setToday">Today</v-btn>
      </h1>
      <p></p>
      <p></p>
      <thead>
        <tr>
          <th>Sunday</th>
          <th>Monday</th>
          <th>Tuesday</th>
          <th>Wednesday</th>
          <th>Thursday</th>
          <th>Friday</th>
          <th>Saturday</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td v-for="(i, index) in CalArray[0]" :key=index>
            <a @click="findSchedule(i)">
              {{ i[2] }}
            </a>
          </td>
        </tr>
        <tr>
          <td v-for="(i, index) in CalArray[1]" :key=index>
            <a @click="findSchedule(i)">
              {{ i[2] }}
            </a>
          </td>
        </tr>
        <tr>
          <td v-for="(i, index) in CalArray[2]" :key=index>
            <a @click="findSchedule(i)">
              {{ i[2] }}
            </a>
          </td>
        </tr>
        <tr>
          <td v-for="(i, index) in CalArray[3]" :key=index>
            <a @click="findSchedule(i)">
              {{ i[2] }}
            </a>
          </td>
        </tr>
        <tr v-show="CalArray[4][0][2] !== ''">
          <td v-for="(i, index) in CalArray[4]" :key=index v-show="i[0][2] !== ''">
            <a @click="findSchedule(i)">
              {{ i[2] }}
            </a>
          </td>
        </tr>
        <tr v-show="CalArray[5][0][2] !== ''">
          <td v-for="(i, index) in CalArray[5]" :key=index v-show="i[0][2] !== ''">
            <a @click="findSchedule(i)">
              {{ i[2] }}
            </a>
          </td>
        </tr>
      </tbody>
  </div>
</template>

<script>
// @ is an alias to /src
/* eslint-disable no-unused-vars */
import Vue from 'vue'
import store from '../store'
import { mapActions } from 'vuex'

export default {
  data: function () {
    return {
      year: 0,
      month: 0,
      dayArray: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
      CalArray: [
        [['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', '']],
        [['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', '']],
        [['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', '']],
        [['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', '']],
        [['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', '']],
        [['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', ''], ['', '', '']]
      ],
      start: 0
    }
  },
  created () {
    this.year = new Date().getFullYear()
    this.month = new Date().getMonth()
    this.start = new Date(this.year, this.month, 1).getDay()
    this.render()
  },
  computed: {
    thisYear () {
      return this.year
    },
    thisMonth () {
      return this.month + 1
    }
  },
  methods: {
    ...mapActions([
      'sendDate'
    ]),
    prevMonth () {
      if (this.month === 0) {
        this.month = 11
        this.year -= 1
      } else {
        this.month -= 1
      }
      this.start = new Date(this.year, this.month, 1).getDay()
      this.render()
    },
    nextMonth () {
      if (this.month === 11) {
        this.month = 0
        this.year += 1
      } else {
        this.month += 1
      }
      this.start = new Date(this.year, this.month, 1).getDay()
      this.render()
    },
    render () {
      this.init()
      if (this.year % 4 === 0 && (this.year % 100 !== 0 || this.year % 400 === 0)) {
        this.dayArray[1] = 29
      } else {
        this.dayArray[1] = 28
      }
      var d = 1
      var col = 0
      var start = this.start
      var flag = false
      for (var k = 0; k < 4; k++) {
        for (var i = (k === 0) ? start : 0; i < 7; i++) {
          this.CalArray[k][i] = [this.year, this.month + 1, d++]
        }
      }
      for (var l = 4; l < 6; l++) {
        for (var j = 0; j < 7; j++) {
          if (d <= this.dayArray[this.month]) {
            this.CalArray[l][j] = [this.year, this.month + 1, d]
            d++
          } else { break }
        }
      }
    },
    init () {
      var arr = new Array([])
      for (var a = 0; a < 6; a++) {
        arr[a] = new Array([])
        for (var b = 0; b < 7; b++) {
          arr[a][b] = ['', '', '']
        }
      }
      this.CalArray = arr
    },
    findSchedule (data) {
      console.log('Calendar.vue에서 findSchedule 호출, data = ', data)
      this.$store.dispatch('sendDate', data)
    },
    setToday () {
      this.year = new Date().getFullYear()
      this.month = new Date().getMonth()
      this.start = new Date(this.year, this.month, 1).getDay() // 매월 1일의 요일을 가져온다
      this.render()
    }
  }
}
</script>

<style scoped>
  table { border-collapse: separate; table-layout: fixed; border: 1px solid #fff; border-spacing: 3px; text-align: center; line-height: 1.5; margin: 20px 10px;}
  th { width: 300px; border: 1px solid #fff; padding: 10px; font-weight: bold; vertical-align: top; color: #fff; background: #ce4869 ;}
  td { width: 300px; height: 150px; border: 1px solid #fff; padding: 10px; vertical-align: top; border-bottom: 1px solid #ccc; background: #eee;}
  h2 { font-size: x-large; }
  h1 { font-size: xx-large;}
</style>
