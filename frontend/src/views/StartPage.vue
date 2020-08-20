<template>
  <div class="startpage">
    <h2>hello</h2>
    <todo-app></todo-app>
  </div>
</template>

<script>
// @ is an alias to /src
// 시작 페이지
import TodoApp from '@/components/TodoApp'

export default {
  name: 'StartPage',
  components: {
    TodoApp

  }
}
</script>
