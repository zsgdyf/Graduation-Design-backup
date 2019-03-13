<template>
  <div id="NavMenu">
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      router
    >
      <el-menu-item index="/index">首页</el-menu-item>
      <el-row
        type="flex"
        class="row-bg"
        justify="end"
      >
        <el-menu-item
          index="/add"
          v-show="unlogin"
        >登录 / 注册</el-menu-item>
        <el-menu-item
          index="/myNotes"
          v-show="logined"
        >欢迎您，{{userName}}</el-menu-item>
        <el-menu-item index="/users">用户列表</el-menu-item>
      </el-row>
    </el-menu>
  </div>
</template>

<script>
export default {
  data () {
    return {
      logined: false,
      unlogin: true,
      userName: ''
    }
  },
  computed: {
    activeIndex () {
      return this.$route.path
    }
  },
  mounted () {
    if (localStorage.userName) {
      this.userName = localStorage.userName
      this.logined = true
      this.unlogin = false
    }
  }
}
</script>
<style scoped>
#NavMenu {
  width: 1140px;
  margin: 0 auto;
}
:focus {
  outline: none;
}
</style>
