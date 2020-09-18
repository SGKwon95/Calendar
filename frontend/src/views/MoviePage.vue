<template>
  <div class="moviepage" align="center">
  <layout>
    <template #content>
      <h1>
        {{ thisYear }}년 &emsp;
        <button @click="prevMonth()" class="page-btn">
        ◀
        </button>
        &nbsp; &nbsp; <b>{{ thisMonth }}월</b> &nbsp; &nbsp;
        <button @click="nextMonth()" class="page-btn">
        ▶
        </button>
         개봉 예정 영화 리스트
        <v-btn rounded color="teal accent-3" dark @click="setMonth">이번 달</v-btn>
      </h1>
      <p></p>
      <table class="movieTable">
        <thead>
          <tr>
            <th class="text-left">영화 (자세한 정보를 보려면 클릭)</th>
            <th class="text-left">개봉일</th>
          </tr>
        </thead>
        <tbody>
            <tr v-for="(releaseDate, movieName, index) in movieList" v-bind:key="index" v-show="showList(releaseDate)">
              <td><a @click="goLink(releaseDate)">{{ movieName }}</a></td>
              <td>{{ releaseDate.split('|')[0] }}</td>
            </tr>
        </tbody>
      </table>
      <br>
      <v-btn rounded color="teal accent-3" dark @click="goBack">돌아가기</v-btn>&nbsp;
    </template>
  </layout>
  </div>
</template>

<script>
/* eslint-disable no-unused-vars */
import axios from 'axios'
import Layout from '@/components/Layout'
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import router from '../router'

export default {
  name: 'MoviePage',
  data () {
    return {
      currentPage: 0,
      currentMonth: new Date().getMonth() + 1
    }
  },
  computed: {
    ...mapState({
      movieList: state => state.movie
    }),
    thisYear () {
      return new Date().getFullYear()
    },
    thisMonth () {
      return this.currentMonth
    },
    ...mapGetters(['isAuthorized'])
  },
  components: {
    Layout
  },
  methods: {
    ...mapActions([
      'getMovieData'
    ]),
    ...mapMutations([
      'GET_MOVIE_LIST'
    ]),
    goBack () {
      this.$router.push({ name: 'StartPage' })
    },
    goLink (link) {
      var tmp = (link + '').split('|')
      window.open(tmp[1], '_blank')
    },
    prevMonth () {
      if (this.currentMonth > new Date().getMonth() + 1) {
        this.currentMonth -= 1
      }
    },
    nextMonth () {
      if (this.currentMonth < 12) {
        this.currentMonth += 1
      }
    },
    setMonth () {
      this.currentMonth = new Date().getMonth() + 1
    },
    showList (data) {
      if (data[4] === '|') {
        return true
      }

      if (Number(data.substring(5, 7)) === this.currentMonth) {
        return true
      } else {
        return false
      }
    }
  },
  created () {
    this.$store.dispatch('getMovieData')
  }
}
</script>

<style scoped>
  table.movieTable {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
  }
  table.movieTable thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
  }
  table.movieTable tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
  }
  table.movieTable td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
  }
</style>
