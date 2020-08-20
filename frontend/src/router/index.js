import Vue from 'vue'
import VueRouter from 'vue-router'
import StartPage from '../views/StartPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'StartPage',
    components: {
      default: StartPage
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
