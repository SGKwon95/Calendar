<template>
  <div>
    <input type="text" v-model="newTodoItem" v-on:keypress.enter="addTodo" class="todoInput">
    &emsp; <v-btn rounded color="teal accent-3" dark @click="addTodo">일정 추가하기</v-btn>
  </div>
</template>

<script>
export default {
  data () {
    return {
      newTodoItem: ''
    }
  },
  methods: {
    addTodo () {
      if (this.newTodoItem !== '') {
        var value = this.newTodoItem && this.newTodoItem.trim()
        console.log(value)
        this.$emit('addTodo', value)
        this.clearInput()
      }
    },
    clearInput () {
      this.newTodoItem = ''
    }
  }
}

</script>

<style scoped>
  .todoInput {
    border: 1px solid;
  }
</style>
