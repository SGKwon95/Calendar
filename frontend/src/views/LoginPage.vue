<template>
  <div align="center">
  <layout/>
    <h2>Login Page</h2>
    <login-form @submit="onSubmit"/>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'
import Layout from '@/components/Layout'
import { mapActions } from 'vuex'

export default {
  name: 'LoginPage',
  components: {
    LoginForm,
    Layout
  },
  methods: {
    onSubmit (payload) {
      console.log('loginPage onSubmit()')
      this.login(payload).then(res => {
        alert('Login Success')
        this.$router.push({ name: 'Home' })
      }).catch(err => {
        // alert(err.response.data.message)
        alert('Something Fail: ', err)
      })
    },
    ...mapActions(['login'])
  }
}
</script>
