import Vue from 'vue'
import VueRouter from 'vue-router'
import StartPage from '../views/StartPage.vue'
import CalendarPage from '../views/CalendarPage.vue'
import LoginPage from '../views/LoginPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'StartPage',
    components: {
      default: StartPage
    }
  },
  {
    path: '/CalendarPage',
    name: 'CalendarPage',
    components: {
      default: CalendarPage
    }
  },
  {
    path: '/LoginPage',
    name: 'LoginPage',
    components: {
      default: LoginPage
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
