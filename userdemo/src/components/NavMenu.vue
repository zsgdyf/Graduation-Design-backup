<template>
  <el-menu
    :default-active="activeIndex"
    mode="horizontal"
    router
  >
    <el-menu-item index="add">登录 / 注册</el-menu-item>
    <el-menu-item index="users">用户列表</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  data () {
    return {}
  },
  computed: {
    activeIndex () {
      return this.$route.path.replace('/', '')
    }
  }
}
</script>
