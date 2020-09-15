<template>
  <div align="center">
    <layout>
      <template #content>
        <sign-up-form v-on:onSubmit="onSubmit"/>
      </template>
    </layout>
  </div>
</template>

<script>
import axios from 'axios'
import Layout from '@/components/Layout'
import SignUpForm from '@/components/SignUpForm'

export default {
  name: 'AdminSetupPage',
  components: {
    SignUpForm,
    Layout
  },
  methods: {
    onSubmit (payload) {
      console.log('payload: ' + payload.userid +
                  ', ' + payload.userName +
                  ', ' + payload.password)
      const userId = payload.userid
      const userName = payload.userName
      const userPw = payload.password
      axios.post('http://localhost:7777/users/setup',
        { userId, userName, userPw })
        .then(res => {
          alert('Register Success')
          this.$router.push({
            name: 'StartPage'
          })
        })
        .catch(err => {
          alert(err)
        })
    }
  }
}
</script>
