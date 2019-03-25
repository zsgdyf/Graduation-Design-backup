<template>
  <div id="add">
    <NavMenu :userName="user.name"></NavMenu>
    <!-- 卡片居中 登录部分 -->
    <el-row
      type="flex"
      class="row-bg"
      justify="center"
    >
      <el-card
        class="loginCard loginForm"
        v-show="showLogin"
        shadow="never"
      >
        <h2>登录</h2>
        <el-form
          ref="logForm"
          :model="user"
          label-width="100px"
        >
          <el-form-item
            label="用户名："
            prop="logName"
          >
            <el-input
              v-model="user.logName"
              clearable
            ></el-input>
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
          <el-form-item>
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-button @click="resetForm('logForm')">清空</el-button>
              <el-button
                native-type='submit'
                type="primary"
                @click="login"
              >登录</el-button>
            </el-row>
          </el-form-item>
        </el-form>
        <el-row
          type="flex"
          class="row-bg"
          justify="space-between"
        >
          <span @click="toReset">忘记密码？</span>
          <span @click="toRegister">还没有账号？点此注册</span>
        </el-row>
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
        shadow="never"
      >
        <h2>注册</h2>
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
          <el-form-item>
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-button @click="resetForm('regForm')">清空</el-button>
              <el-button
                native-type='submit'
                type="primary"
                @click="submitForm('regForm')"
              >注册</el-button>
            </el-row>
          </el-form-item>
        </el-form>
        <p @click="toLogin">已有账号，点此登录</p>
      </el-card>
    </el-row>
    <!-- 重置密码 -->
    <el-row
      type="flex"
      class="row-bg"
      justify="center"
    >
      <el-card
        class="resetCard resetForm"
        v-show="showReset"
        shadow="never"
      >
        <h2>重置密码</h2>
        <el-form
          ref="resetForm"
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
          <el-form-item>
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-button @click="resetForm('resetForm')">清空输入</el-button>
              <el-button
                native-type='submit'
                type="primary"
                @click="submitForm('resetForm')"
              >确认重置</el-button>
            </el-row>
          </el-form-item>
        </el-form>
        <p @click="toLogin"  class="returnToLogin">返回登录</p>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios'
import NavMenu from '@/components/NavMenu.vue'
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
      showRegister: false,
      showReset: false
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
          window.localStorage['userName'] = response.data.user.name
          this.$router.push({ path: '/' })
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
          this.showLogin = true
          // this.$router.push({ path: '/users' })
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
      this.showRegister = true
      this.showLogin = false
      this.showReset = false
    },
    toLogin () {
      this.showLogin = true
      this.showRegister = false
      this.showReset = false
    },
    toReset () {
      this.showReset = true
      this.showLogin = false
      this.showRegister = false
    }
  }
}
</script>

<style scoped>
#add {
  width: 80%;
  margin: 0 auto;
  text-align: center;
}
.loginCard,
.registerCard,
.resetCard {
  /* display: flex;
  justify-content: center; */
  /* width: 35rem; */
  margin-top: 1rem;
  border: none;
}
.el-form {
  width: 30rem;
  margin: 1rem auto;
}
span {
  text-align: left;
  cursor: pointer;
  color: #606266;
  text-decoration-line: underline;
}
p {
  text-align: right;
  cursor: pointer;
  color: #606266;
  text-decoration-line: underline;
}
p.returnToLogin {
  text-align: left;
}
</style>
