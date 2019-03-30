<template>
  <div id="SideNavMenu">
    <el-menu
      :default-active="activeIndexSideNav"
      router
    >
      <el-menu-item index="/editor">创建笔记</el-menu-item>
      <el-menu-item index="/user">个人信息</el-menu-item>
      <el-menu-item index="/myNotes">我的笔记</el-menu-item>
      <el-menu-item index="/myLove">我的收藏</el-menu-item>
      <el-menu-item @click="logout">注销登录</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeIndexSideNav: this.$route.path
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
    this.defaultIndex()
  }
}
</script>

<style scoped>
#SideNavMenu {
  width: 10rem;
}
</style>
