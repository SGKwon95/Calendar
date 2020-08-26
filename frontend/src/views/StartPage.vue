<template>
  <div class="startpage">
    <layout/>
  </div>
</template>

<script>
// @ is an alias to /src
// 시작 페이지
/* eslint-disable no-unused-vars */
import Vue from 'vue'
import store from '../store'
import Layout from '@/components/Layout'

export default {
  name: 'StartPage',
  components: {
    Layout
  }
}
</script>
