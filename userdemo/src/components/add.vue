<template>
  <div>
    <NavMenu></NavMenu>
    <!-- 卡片居中 登录部分 -->
    <el-row
      type="flex"
      class="row-bg"
      justify="center"
    >
      <el-card
        class="loginCard loginForm"
        v-show="showLogin"
      >
        <el-form
          ref="logForm"
          :model="user"
          label-width="100px"
        >
          <el-form-item
            label="用户名："
            prop="logName"
          >
            <el-input v-model="user.logName" clearable></el-input>
          </el-form-item>
          <el-form-item
            label="密码："
            prop="logPassword"
          >
            <el-input
              v-model="user.logPassword"
              type="password"
            >
            </el-input>
          </el-form-item>
          <p @click="toRegister">还没有账号？点此注册</p>
          <el-form-item>
            <el-button @click="resetForm('logForm')">重置</el-button>
            <el-button
              native-type='submit'
              type="primary"
              @click="login"
            >登录</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-row>
    <!-- 注册部分 -->
    <el-row
      type="flex"
      class="row-bg"
      justify="center"
    >
      <el-card
        class="registerCard registerForm"
        v-show="showRegister"
      >
        <el-form
          ref="regForm"
          :model="user"
          status-icon
          :rules="rules"
          label-width="100px"
        >
          <el-form-item
            label="用户名："
            prop="name"
          >
            <el-input v-model="user.name"></el-input>
          </el-form-item>
          <el-form-item
            label="密码："
            prop="password"
          >
            <el-input
              v-model="user.password"
              type="password"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="确认密码："
            prop="checkPassword"
          >
            <el-input
              v-model="user.checkPassword"
              type="password"
            ></el-input>
          </el-form-item>
          <p @click="toLogin">已有账号，点此登录</p>
          <el-form-item>
            <el-button @click="resetForm('regForm')">重置</el-button>
            <el-button
              native-type='submit'
              type="primary"
              @click="submitForm('regForm')"
            >注册</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-row>
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
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.user.password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      user: {
        name: '',
        password: '',
        checkPassword: '',
        logName: '',
        logPassword: ''
      },
      rules: {
        name: [{
          required: true, message: '用户名不能为空', trigger: 'blur'
        }],
        password: [{
          required: true, message: '请输入密码', trigger: 'blur'
        }],
        checkPassword: [{
          validator: validatePass2, trigger: 'change'
        }]
      },
      // 默认先显示登录部分
      showLogin: true,
      showRegister: false
    }
  },
  methods: {
    login () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: '&name=' + this.user.logName + '&password=' + this.user.logPassword
      }).then(response => {
        if (response.data.message === '用户不存在！') {
          this.$message.error(response.data.message)
          console.log(response.data)
        } else if (response.data.message === '密码错误！') {
          this.$message.error(response.data.message)
          console.log(response.data)
        } else {
          this.$message({
            message: '欢迎回来！  ' + response.data.user.name,
            type: 'success'
          })
          // 将返回的 token 存储到本地
          window.localStorage['token'] = JSON.stringify(response.data.token)
          this.$router.push({ path: '/users' })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    addUser () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/insert',
        data: '&name=' + this.user.name + '&password=' + this.user.password
      }).then(response => {
        if (response.data.message === '用户名已被使用！') {
          this.$message.error(response.data.message)
          console.log(response.data.message)
        } else {
          this.$message({
            message: '注册成功！',
            type: 'success'
          })
          this.$router.push({ path: '/users' })
        }
      })
        .catch(error => {
          console.log(error)
        })
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.addUser()
        } else {
          console.log('fail submit!!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    toRegister () {
      this.showLogin = false
      this.showRegister = true
    },
    toLogin () {
      this.showLogin = true
      this.showRegister = false
    }
  }
}
</script>

<style scoped>
.loginCard,
.registerForm {
  /* display: flex;
  justify-content: center; */
  width: 500px;
  margin-top: 50px;
}
.el-form {
  width: 460px;
  margin: 20px auto;
}
p {
  margin-left: 80px;
}
</style>
