<template>
  <div class="schedulemanagepage" align="center">
  <layout>
    <template #content>
      <h2>{{ scheduleList[0].date }}&nbsp; 일정표</h2>
      <table>
        <tr v-for="list in scheduleList" v-bind:key="list.contentId">
          <td>{{ list.contentId }}</td>
          <td>{{ list.content }}</td>
        </tr>
      </table>
    </template>
  </layout>
  </div>
</template>

<script>
/* eslint-disable no-unused-vars */
import Layout from '@/components/Layout'
import { mapState } from 'vuex'

export default {
  name: 'ScheduleManagePage',
  data () {
    return {
    }
  },
  computed: {
    ...mapState({
      scheduleList: state => state.schedule
    })
  },
  components: {
    Layout
  },
  methods: {
  }
}
</script>
