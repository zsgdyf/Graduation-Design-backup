<template>
  <div>
    <NavMenu></NavMenu>
    <div class="loginForm">
      <el-form
        ref="form"
        v-model="user"
        label-width="80px"
      >
        <el-form-item label="用户名：">
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="密码：">
          <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button>取消</el-button>
          <el-button
            native-type='submit'
            type="primary"
            @click="addUser"
          >立即添加</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import NavMenu from './NavMenu.vue'
export default {
  components: {
    NavMenu
  },
  data () {
    return {
      user: {
        name: '',
        password: ''
      },
      activeIndex: '1'
    }
  },
  methods: {
    addUser () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/insert',
        data: '&name=' + this.user.name + '&password=' + this.user.password
      }).then(response => {
        console.log(response)
        this.$router.push({ path: '/users' })
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
.loginForm {
  display: flex;
  justify-content: center;
}
.el-form {
  width: 480px;
  margin-top: 50px;
}
tr {
  border: 1px solid #000;
}
</style>
