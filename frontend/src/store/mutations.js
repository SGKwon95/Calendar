import {
/* eslint-disable no-unused-vars */
  SEARCH_TODO,
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO,
  GET_MOVIE_LIST,
  BRING_ID
} from './mutation-types'

import axios from 'axios'
import cookies from 'vue-cookies'

export default {
  [SEARCH_TODO] (state, payload) {
    state.schedule = payload
  },
  [SET_ACCESS_TOKEN] (state, accessToken) {
    if (accessToken) {
      state.accessToken = accessToken
      axios.defaults.headers.common.Authorization = `Bearer ${accessToken}`

      cookies.set('accessToken', accessToken, '1h')
    }
  },
  [SET_MY_INFO] (state, myinfo) {
    if (myinfo) {
      state.myinfo = myinfo
    }
  },
  [DESTROY_ACCESS_TOKEN] (state) {
    state.accessToken = ''
    delete axios.defaults.headers.common.Authorization
    cookies.remove('accessToken')
  },
  [DESTROY_MY_INFO] (state) {
    state.myinfo = null
  },
  [GET_MOVIE_LIST] (state, payload) {
    state.movie = payload
  },
  [BRING_ID] (state, payload) {
    state.id = payload
  }
}
