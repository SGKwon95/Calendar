import {
/* eslint-disable no-unused-vars */
  ADD_TODO,
  SEARCH_TODO,
  REMOVE_TODO,
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO,
  SET_TMP_DATE
} from './mutation-types'

import axios from 'axios'
import router from '../router'

export default {
  sendDate ({ commit, dispatch }, dateArray) {
    console.log('Calender.vue 에서 dateArray dispatch 성공. data = ', dateArray)
    const m = (dateArray[1] < 9) ? ('0' + dateArray[1]) : dateArray[1]
    const d = (dateArray[2] < 10) ? ('0' + dateArray[2]) : dateArray[2]
    const df = dateArray[0] + '-' + m + '-' + d
    console.log('df = ', df)

    axios.get(`http://localhost:9999/schedule/${df}`)
      .then(res => {
        commit(SEARCH_TODO, res.data)
        router.push({ name: 'ScheduleManagePage' })
      })
  }
}
