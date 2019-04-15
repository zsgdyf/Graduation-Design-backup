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
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img
                v-if="imageUrl"
                :src="imageUrl"
                class="avatar"
              >
              <i
                v-else
                class="el-icon-plus avatar-uploader-icon"
              ></i>
            </el-upload>
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
      labels: [],
      imageUrl: ''
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
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
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

<style>
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
.el-upload {
  border: 2px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
}
.el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
