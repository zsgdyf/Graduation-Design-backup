<template>
  <div id="SideNavMenu">
    <el-menu :default-active="activeIndex">
      <el-menu-item index="/userInfo">个人信息</el-menu-item>
      <el-menu-item index="/myNotes">我的笔记</el-menu-item>
      <el-menu-item index="/myLove">我的收藏</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data () {
    return {}
  },
  computed: {
    activeIndex () {
      return this.$route.path
    }
  }
}
</script>

<style scoped>
#SideNavMenu {
  width: 240px;
}
</style>
