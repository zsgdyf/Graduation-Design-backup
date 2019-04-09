<template>
  <div id="NavMenu">
    <el-menu
      :default-active="activeIndexNav"
      mode="horizontal"
      router
    >
      <el-menu-item>
        <router-link to="/">
          <img
            src="@/assets/logo-1.png"
            width="111px"
            height="56px"
          >
        </router-link>
      </el-menu-item>
      <!-- <el-menu-item index="/index">首页</el-menu-item> -->
      <el-row
        type="flex"
        class="row-bg"
        justify="end"
      >
        <el-menu-item
          index="/add"
          v-show="unlogin"
        >登录 / 注册</el-menu-item>
        <el-submenu
          v-show="logined"
          index=""
        >
          <template slot="title">欢迎您，{{userName}}</template>
          <el-menu-item index="/myNotes">我的笔记</el-menu-item>
          <el-menu-item index="/editor">创建笔记</el-menu-item>
          <el-menu-item index="/user">个人信息</el-menu-item>
          <el-menu-item @click="logout">注销登录</el-menu-item>
        </el-submenu>
        <el-menu-item index="/userList">用户列表</el-menu-item>
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
      userName: '',
      activeIndexNav: this.$route.path
    }
  },
  methods: {
    defaultIndex () {
      this.activeIndexSideNav = this.$route.path
    },
    logout () {
      window.localStorage.clear()
      this.$router.push({ path: '/' })
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
  width: 100%;
  margin: 0 auto;
}
.el-submenu {
  width: 10rem;
}
:focus {
  outline: none;
}
</style>
