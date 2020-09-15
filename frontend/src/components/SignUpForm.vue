<template>
  <v-app>
    <v-content>
      <v-container class="fill-heigh" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="8">
            <v-card class="elevation-12">
              <v-window>
                <v-window-item :value="1">
                  <v-row>
                    <v-col cols="12" md="12">
                      <v-card-text class="mt-12">
                        <h1 class="text-center display-2 teal--text text--accent-3">Sign Up Page</h1>
                        <br>
                        <v-form>
                          <v-text-field
                          label="ID"
                          v-model="userid"
                          type="text"
                          color="teal accent-3"
                          />
                          <v-text-field
                          id="userName"
                          label="userName"
                          v-model="userName"
                          type="text"
                          color="teal accent-3"/>
                          <v-text-field
                          id="password"
                          label="Password"
                          v-model="password"
                          type="password"
                          color="teal accent-3"/>
                        </v-form>
                      </v-card-text>
                      <div class="text-center mt-3">
                        <v-btn rounded color="teal accent-3" dark @click="submit">SIGN UP</v-btn>
                        &nbsp; <v-btn rounded color="teal accent-3" dark>BACK</v-btn>
                      </div>
                    </v-col>
                </v-row>
                </v-window-item>
              </v-window>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
/* eslint-disable no-unused-vars */
import { mapActions } from 'vuex'
import router from '../router'

export default {
  name: 'LoginPage',
  data () {
    return {
      loading: false,
      message: '',
      userid: '',
      password: '',
      userName: ''
    }
  },
  methods: {
    submit () {
      const { userid, userName, password } = this
      console.log('id = ', this.userid)
      console.log('name = ', this.userName)
      console.log('pw = ', this.password)
      this.$emit('onSubmit', { userid, userName, password })
    },
    back () {
      this.$router.push('./StartPage')
    }
  }
}
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
