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
            <p>头像：</p>
            <p>用户名：{{user.name}}</p>
            <p>昵称：</p>
            <p>个人介绍：用一句话介绍下你自己吧~</p>
            <span>我关注的标签：</span>
            <el-tag
              :key="label.id"
              v-for="label in labels"
            >{{label.content}}</el-tag>
          </div>
        </div>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import NavMenu from '@/components/NavMenu.vue'
import SideNavMenu from '@/components/SideNavMenu.vue'
export default {
  components: {
    NavMenu,
    SideNavMenu
  },
  data () {
    return {
      user: [],
      labels: []
    }
  },
  methods: {
    getUserData () {
      this.user.name = localStorage.userName
    },
    getTags () {
      let userId = window.localStorage.userId
      axios.get('http://localhost:8080/getUserLabel?userId=' + userId).then(response => {
        this.labels = response.data
      }).catch(error => {
        console.log(error)
      })
    }
  },
  created () {
    this.getUserData()
  },
  mounted () {
    this.getTags()
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
.info-content {
  padding-left: 2rem;
}
p {
  margin-bottom: 2rem;
}
.el-tag {
  margin-left: 0.6rem;
}
</style>
