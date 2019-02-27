<template>
  <div>
    <NavMenu></NavMenu>
    <div
      class="loginForm"
      v-show="showLogin"
    >
      <el-form
        ref="form"
        v-model="user"
        label-width="100px"
      >
        <el-form-item
          label="用户名："
          prop="userName"
        >
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item
          label="密码："
          prop="userPassword"
        >
          <el-input v-model="user.password"></el-input>
        </el-form-item>
        <p @click="toRegister">还没有账号？点此注册</p>
        <el-form-item>
          <el-button>取消</el-button>
          <el-button
            native-type='submit'
            type="primary"
            @click="addUser"
          >登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div
      class="registerForm"
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
        password: ''
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
      showLogin: true,
      showRegister: false
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
.loginForm,
.registerForm {
  display: flex;
  justify-content: center;
}
.el-form {
  width: 480px;
  margin-top: 50px;
}
p {
  margin-left: 80px;
}
tr {
  border: 1px solid #000;
}
</style>
