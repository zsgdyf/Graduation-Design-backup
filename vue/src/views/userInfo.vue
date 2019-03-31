<template>
  <div id="userInfo">
    <NavMenu></NavMenu>
    <div id="content">
      <el-row
        type="flex"
        class="row-bg"
        justify="center"
      >
        <SideNavMenu></SideNavMenu>
        <div class="info-container">
          <div class="info-content">
            <h2>{{user.name}}，你好！</h2>
          </div>
        </div>
      </el-row>
    </div>
  </div>
</template>

<script>
import NavMenu from '@/components/NavMenu.vue'
import SideNavMenu from '@/components/SideNavMenu.vue'
export default {
  components: {
    NavMenu,
    SideNavMenu
  },
  data () {
    return {
      user: []
    }
  },
  methods: {
    getUserData () {
      this.user.name = localStorage.userName
    }
  },
  created () {
    this.getUserData()
  }
}
</script>

<style scoped>
#userInfo {
  width: 80%;
  margin: 0 auto;
}
#content {
  margin-top: 2rem;
}
.info-container {
  width: 80%;
}
.info-content{
  padding-left: 2rem;
}
</style>
