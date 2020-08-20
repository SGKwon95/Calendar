<template>
  <div class="startpage">
    <todo-app/>
  </div>
</template>

<script>
// @ is an alias to /src
// 시작 페이지
/* eslint-disable no-unused-vars */
import TodoApp from '@/components/TodoApp'
import Vue from 'vue'
import store from '../store'

export default {
  name: 'StartPage',
  components: {
    TodoApp
  }
}
</script>
