<template>
  <div class="startpage">
    <layout>
      <template #content>
        <div align="center">
          <h2 v-if="isAuthorized"> {{ myinfo.auth }}계정, 접속을 환영합니다</h2>
          <div v-else>
            <h2><button @click="goLoginPage" class="login-btn">로그인</button>이 필요합니다</h2>
          </div>
        </div>
      </template>
    </layout>
  </div>
</template>

<script>
// @ is an alias to /src
// 시작 페이지
/* eslint-disable no-unused-vars */
import Vue from 'vue'
import store from '../store'
import Layout from '@/components/Layout'
import { mapGetters, mapState, mapActions } from 'vuex'

export default {
  name: 'StartPage',
  components: {
    Layout
  },
  computed: {
    ...mapState(['myinfo']),
    ...mapGetters(['isAuthorized'])
  },
  methods: {
    onClickLogout () {
      this.logout()
      alert('Success Logout')
      this.$router.push({ name: 'StartPage' })
    },
    ...mapActions(['logout', 'goLoginPage'])
  },
  data () {
    return {
      ...mapState(['accessToken'])
    }
  }
}
</script>

<style scoped>
  .login-btn {
    color: blue;
  }
</style>
