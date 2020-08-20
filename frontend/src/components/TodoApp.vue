<template>
  <div>
    <todo-item/>
    <todo-creator/>
  </div>
</template>

<script>
import TodoCreator from './TodoCreator'
import TodoItem from './TodoItem'

export default {
  components: {
    TodoCreator,
    TodoItem
  }
}
</script>

<style scoped>
  div {
    background-color: #77cce3
  }
</style>
