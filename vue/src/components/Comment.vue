<template>
  <div id="comment">
    <div id="comment-input">
      <el-row
        type="flex"
        class="row-bg"
        justify-content="space-between"
        align-items="flex-end"
      >
        <el-input
          type="textarea"
          autosize
          placeholder="请输入评论"
          v-model="comment"
        >
        </el-input>
        <el-button
          type="primary"
          @click="submit"
        >发布</el-button>
      </el-row>
    </div>
    <div id="content-container">
      <el-card
        v-for="(item,index) in comments"
        :key="index"
        shadow="never"
      >
        <vue-markdown :source="item.content"></vue-markdown>
        <span class="author">{{item.author}} </span><span class="create-time">评论于 {{item.createTime}}</span>
        <span
          class="action"
          @click="deleteComment(item.id)"
        >删除</span>
      </el-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import VueMarkdown from 'vue-markdown'
import { setTimeout } from 'timers'
export default {
  components: {
    VueMarkdown
  },
  props: { articleId: Number },
  data () {
    return {
      comment: '',
      comments: []
    }
  },
  methods: {
    submit () {
      let author = window.localStorage.userName
      let content = this.comment
      let currentDate = new Date()
      let createTime = currentDate.toLocaleString('zh', { hour12: false })
      axios({
        method: 'post',
        url: 'http://localhost:8080/newComment',
        data: {
          author: author,
          content: content,
          createTime: createTime,
          articleId: this.articleId
        }
      }).then(response => {
        if (response.data.message === '发布成功！') {
          this.$message({
            message: '发布成功！',
            type: 'success'
          })
        }
        this.getData()
      }).catch(error => {
        console.log(error)
      })
    },
    getData () {
      axios.get(`http://localhost:8080/getCommentByArticleId?articleId=${this.articleId}`).then(response => {
        this.comments = response.data
        this.comments.reverse()
      }).catch(error => {
        console.log(error)
      })
    },
    deleteComment (id) {
      axios.get(`http://localhost:8080/deleteComment?id=${id}`).then(response => {
        if (response.data.message === '删除成功！') {
          this.$message({
            message: '删除成功！',
            type: 'success'
          })
        }
        this.getData()
      }).catch(error => {
        console.log(error)
      })
    }
  },
  mounted () {
    // 如果不延时执行的话 this.articleId 会取不到数据（为 undefined）
    setTimeout(() => {
      this.getData()
    }, 100)
  }
}
</script>

<style lang="scss">
#comment {
  width: 80%;
  margin: {
    left: 2rem;
    bottom: 2rem;
  }
  #comment-input {
    margin: {
      bottom: 1rem;
    }
  }
  // 多行输入框字体会继承浏览器默认样式
  // 需重新覆盖一次全局字体
  .el-textarea__inner {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
  }
  .el-button {
    height: 33px;
    margin: {
      left: 0.6rem;
    }
  }
  .el-card__body {
    padding: 5px;
  }
  span {
    &.author {
      color: #409eff;
    }
    &.create-time {
      color: #909399;
    }
  }
}
</style>
