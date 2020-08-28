import {
/* eslint-disable no-unused-vars */
  ADD_TODO,
  SEARCH_TODO,
  REMOVE_TODO,
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO
} from './mutation-types'

export default {
  [SEARCH_TODO] (state, payload) {
    state.schedule = payload
  }
}
