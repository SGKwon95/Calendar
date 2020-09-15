<template>
  <div class="schedulemanagepage" align="center">
  <layout>
    <template #content>
      <h1>{{ thisYear }}년&nbsp; {{ thisMonth }}월&nbsp; {{ thisDay }}일</h1>
      <br>
      <p v-if="scheduleList.length === 1">오늘의 일정이 없습니다.</p>
      <table class="scheduleTable">
        <thead v-if="scheduleList.length !== 1 || newSchedule.length !== 0">
          <tr>
            <th class="text-left">Todo List</th>
            <th class="text-left">Complete</th>
          </tr>
        </thead>
        <tbody>
            <tr v-for="list in scheduleList" v-bind:key="list.contentId">
              <td v-if="list.contentId !== -1">{{ list.content }}</td>
              <td v-if="list.contentId !== -1"><button @click="deleteSchedule(list.contentId)"><v-icon>mdi-check</v-icon></button></td>
            </tr>
            <tr v-for="(list, index) in newSchedule" v-bind:key="index">
              <td>{{ list }}</td>
              <td><button v-on:click="deleteTodo(index)"><v-icon>mdi-close</v-icon></button></td>
            </tr>
        </tbody>
      </table>
      <br>
      <todo-input @addTodo="addSchedule"/>
      <br>
      <form @submit.prevent="onSubmit">
      <v-btn rounded color="teal accent-3" dark type="submit">저장하기</v-btn>&nbsp;
      <v-btn rounded color="teal accent-3" dark @click="goBack">돌아가기</v-btn>&nbsp;
      </form>
    </template>
  </layout>
  </div>
</template>

<script>
/* eslint-disable no-unused-vars */
import axios from 'axios'
import Layout from '@/components/Layout'
import TodoInput from '@/components/TodoInput'
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import router from '../router'

export default {
  name: 'ScheduleManagePage',
  data () {
    return {
      currentDate: '',
      newSchedule: [],
      content: '',
      dateString: '',
      todoNo: 0
    }
  },
  computed: {
    ...mapState({
      scheduleList: state => state.schedule
    }),
    thisYear () {
      return this.scheduleList[0].dateString.substring(0, 4)
    },
    thisMonth () {
      return this.scheduleList[0].dateString.substring(5, 7)
    },
    thisDay () {
      return this.scheduleList[0].dateString.substring(8)
    },
    ...mapGetters(['isAuthorized'])
  },
  components: {
    Layout,
    TodoInput
  },
  methods: {
    ...mapActions([
      'deleteSchedule'
    ]),
    ...mapMutations([
      'SEARCH_TODO'
    ]),
    async onSubmit () {
      if (Array.isArray(this.newSchedule) && this.newSchedule.length !== 0) {
        this.dateString = this.scheduleList[0].dateString
        for (var i = 0; i < this.newSchedule.length; i++) {
          this.content = this.newSchedule[i]
          const { content, dateString } = this
          await axios.post('http://localhost:7777/schedule', { content, dateString })
            .then(res => {
              console.log('저장 성공, data = ', res)
            })
            .catch(err => {
              console.log(err)
            })
        }
        this.newSchedule = []
        var df = this.dateString
        var reload = false
        await this.$store.dispatch('Refresh', { df, reload })
      }
    },
    addSchedule (data) {
      this.newSchedule.push(data)
    },
    deleteTodo (index) {
      this.newSchedule.splice(index, 1)
    },
    deleteSchedule (todoNum) {
      this.todoNo = todoNum
      this.dateString = this.scheduleList[0].dateString
      const { todoNo, dateString } = this
      this.$store.dispatch('deleteSchedule', { todoNo, dateString })
    },
    goBack () {
      this.$router.push({ name: 'CalendarPage' })
    }
  }
}
</script>

<style scoped>
  table.scheduleTable {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
  }
  table.scheduleTable thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
  }
  table.scheduleTable tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
  }
  table.scheduleTable td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
  }
</style>
