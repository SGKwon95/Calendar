import {
/* eslint-disable no-unused-vars */
  ADD_TODO,
  SEARCH_TODO,
  REMOVE_TODO,
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO,
  GET_MOVIE_LIST,
  BRING_ID
} from './mutation-types'

import axios from 'axios'
import router from '../router'

export default {
  sendDate ({ dispatch }, dateArray) {
    console.log('Calender.vue 에서 dateArray dispatch 성공. data = ', dateArray)
    const m = (dateArray[1] < 10) ? ('0' + dateArray[1]) : dateArray[1]
    const d = (dateArray[2] < 10) ? ('0' + dateArray[2]) : dateArray[2]
    const df = dateArray[0] + '-' + m + '-' + d
    console.log('df = ', df)
    var reload = true
    dispatch('Refresh', { df, reload })
  },
  deleteSchedule ({ dispatch, state }, payload) {
    const todoNo = payload.todoNo
    const df = payload.dateString
    console.log('todoNo = ', todoNo)
    console.log('df = ', df)
    console.log('userNo = ', state.myinfo.userNo)
    var reload = false
    axios.get(`http://localhost:7777/schedule/remove/${df}/${todoNo}/${state.myinfo.userNo}`)
      .then(res => {
        console.log('delete complete')
        dispatch('Refresh', { df, reload })
      })
      .catch(err => {
        console.log(err)
      })
  },
  Refresh ({ state, commit }, payload) {
    axios.get(`http://localhost:7777/schedule/search/${payload.df}/${state.myinfo.userNo}`)
      .then(res => {
        commit(SEARCH_TODO, res.data)
        if (payload.reload) router.push('ScheduleManagePage')
      })
      .catch(err => {
        console.log(err)
      })
  },
  login ({ commit }, payload) {
    console.log('actions login')
    return axios.post(`http://localhost:7777/api/authenticate?username=${payload.userid}&password=${payload.password}`, {
      username: payload.userid,
      password: payload.password
    }).then(res => {
      console.log('actions after post, res=', res)
      const { authorization } = res.headers
      const accessToken = authorization.substring(7)
      commit(SET_ACCESS_TOKEN, accessToken)

      return axios.get('http://localhost:7777/users/myinfo')
    }).then(res => {
      console.log('After Get Auth Info')
      console.log(res.data)
      commit(SET_MY_INFO, res.data)
      commit(BRING_ID, payload.userid)
    })
  },
  loginByToken ({ commit }, token) {
    commit(SET_ACCESS_TOKEN, token)
    return axios.get('http://localhost:7777/users/myinfo')
      .then(res => {
        commit(SET_MY_INFO, res.data)
      })
  },
  logout ({ commit }) {
    commit(DESTROY_MY_INFO)
    commit(DESTROY_ACCESS_TOKEN)
    router.push({ name: 'StartPage' })
  },
  goLoginPage () {
    router.push({ name: 'LoginPage' })
  },
  getMovieData ({ commit }) {
    console.log('getMovieData')
    return axios.get('http://127.0.0.1:5000/')
      .then(res => {
        commit(GET_MOVIE_LIST, res.data)
      })
      .catch(err => {
        alert('불러오기 실패, python server를 구동시키세요.')
        console.log(err)
      })
  }
}
