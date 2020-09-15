import Vue from 'vue'
import VueRouter from 'vue-router'
import StartPage from '../views/StartPage.vue'
import CalendarPage from '../views/CalendarPage.vue'
import LoginPage from '../views/LoginPage.vue'
import ScheduleManagePage from '../views/ScheduleManagePage.vue'
import SignUpPage from '../views/SignUpPage.vue'
import MoviePage from '../views/MoviePage.vue'

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
  },
  {
    path: '/ScheduleManagePage',
    name: 'ScheduleManagePage',
    components: {
      default: ScheduleManagePage
    }
  },
  {
    path: '/SignUpPage',
    name: 'SignUpPage',
    components: {
      default: SignUpPage
    }
  },
  {
    path: '/MoviePage',
    name: 'MoivePage',
    components: {
      default: MoviePage
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
