<template>
  <el-menu
    :default-active="activeIndex"
    mode="horizontal"
    router
  >
    <el-menu-item index="add">新增用户</el-menu-item>
    <el-menu-item index="users">用户列表</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  computed: {
    activeIndex () {
      return this.$route.path.replace('/', '')
    }
  }
}
</script>
