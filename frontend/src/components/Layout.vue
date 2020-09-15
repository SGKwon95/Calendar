<template>
  <v-app id="inspire">
    <v-card class="overflow-hidden">
    <v-app-bar
      absolute
      color="#43a047"
      dark
      shrink-on-scroll
      prominent
      src="https://picsum.photos/1920/1080?random"
      fade-img-on-scroll
      scroll-threshold="500"
    >
      <template v-slot:img="{ props }">
        <v-img
          v-bind="props"
          gradient="to top right, rgba(55,236,186,.7), rgba(25,32,72,.7)"
        ></v-img>
      </template>

      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title @click="calendar">My Calendar</v-toolbar-title>

      <v-spacer></v-spacer>
      <slot name="meuebar"></slot>
      <!-- v-icon name list -->
      <!-- https://cdn.materialdesignicons.com/1.1.34/ -->
      <input type="text" v-model="search" v-on:keypress.enter="searchTodo" class="searchBar">
      <v-btn icon @click="searchTodo">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon @click="goLoginPage" v-if="!isAuthorized">
        <v-icon>mdi-login</v-icon>
      </v-btn>
      <v-btn icon @click="logout" v-else>
        <v-icon>mdi-logout</v-icon>
      </v-btn>
      <v-btn icon @click="movie">
        <v-icon>mdi-movie</v-icon>
      </v-btn>
    </v-app-bar>
    <v-navigation-drawer
          v-model="drawer"
          app
        >
          <v-list dense>
            <v-list-item @click.stop="left = !left" v-if="!isAuthorized">
              <v-list-item-action>
                <v-icon>mdi-login</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title @click="login">Log in</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item @click.stop="left = !left" v-else>
              <v-list-item-action>
                <v-icon>mdi-logout</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title @click="logout">Log out</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense>
            <v-list-item @click.stop="left = !left">
              <v-list-item-action>
                <v-icon>mdi-exit-to-app</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title @click="home">Home</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense>
            <v-list-item @click.stop="left = !left" v-if="isAuthorized">
              <v-list-item-action>
                <v-icon>mdi-exit-to-app</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title @click="calendar">Calendar</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense>
            <v-list-item @click.stop="left = !left" v-if="isAuthorized">
              <v-list-item-action>
                <v-icon>mdi-exit-to-app</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title @click="movie">Opening this year Movie</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

        </v-navigation-drawer>

        <div style="height: 150px;"></div>
        <v-content id="content">
          <slot name="content" class="font">
          </slot>
        </v-content>
        <div style="height: 150px;"></div>
  </v-card>
</v-app>
</template>

<script>
import router from '../router'
import { mapActions, mapGetters } from 'vuex'
export default {
  data: () => ({
    search: '',
    drawer: false,
    left: false
  }),
  computed: {
    ...mapGetters(['isAuthorized'])
  },
  methods: {
    ...mapActions([
      'Refresh', 'goLoginPage', 'logout'
    ]),
    searchTodo () {
      if (this.isAuthorized) {
        if (this.search !== '') {
          if (this.isCorrectDate()) {
            const reload = true
            const df = this.search
            this.$store.dispatch('Refresh', { df, reload })
            this.search = ''
          } else {
            alert('날짜를 yyyy-MM-dd 포맷으로 10자리 형식으로 정확하게 입력하세요. ex) 2020-09-01')
          }
        }
      }
    },
    home () {
      (window.location.pathname !== '/') ? router.push('/') : router.go(0)
    },
    login () {
      (window.location.pathname !== '/LoginPage') ? router.push('/LoginPage') : router.go(0)
    },
    calendar () {
      if (this.isAuthorized) {
        (window.location.pathname !== '/CalendarPage') ? router.push('/CalendarPage') : router.go(0)
      }
    },
    movie () {
      if (this.isAuthorized) {
        (window.location.pathname !== '/MoviePage') ? router.push('/MoviePage') : router.go(0)
      }
    },
    isCorrectDate () {
      if (this.search.length === 10 && this.search[4] === '-' && this.search[7] === '-') {
        var y = Number(this.search.substring(0, 4))
        var m = Number(this.search.substring(5, 7))
        var d = Number(this.search.substring(8))
        var dayArray = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        // 윤년이면 2월을 29일로 설정함
        if (y % 4 === 0 && (y % 100 !== 0 || y % 400 === 0)) {
          dayArray[1] = 29
        }
        if (y > 0 && (m >= 1 && m <= 12) && (d >= 1 && d <= dayArray[m - 1])) {
          return true
        }
      }
      return false
    }
  }
}
</script>

<style scoped>
.searchBar {
  background: #ffffff;
}
.overflow-hidden {
  width: 100%;
}
</style>
