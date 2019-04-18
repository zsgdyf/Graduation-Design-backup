<template>
  <div id="articles">
    <NavMenu></NavMenu>
    <div class="article-content">
      <h1>{{article.title}}</h1>
      <p class="author-info">作者：{{article.author}}&nbsp;&nbsp;
        发布于：{{article.create_time}}
        &nbsp;&nbsp;收藏数：{{article.love_count}}
        <span
          @click="loveArticle"
          class="action"
          v-show="love"
        >收藏</span>
        <span
          @click="cancelLoveArticle"
          class="action"
          v-show="loved"
        >已收藏</span>
      </p>
      <div>
        <vue-markdown :source="article.content_md"></vue-markdown>
      </div>
      <hr>
      <div class="tags">
        <el-tag
          :key="label.id"
          v-for="label in labels"
        >{{label.content}}</el-tag>
      </div>
    </div>
    <Comment></Comment>
  </div>
</template>

<script>
import axios from 'axios'
import VueMarkdown from 'vue-markdown'
import Prism from 'prismjs'
import NavMenu from '@/components/NavMenu.vue'
import SideNavMenu from '@/components/SideNavMenu.vue'
import Comment from '@/components/Comment.vue'
export default {
  components: {
    NavMenu,
    SideNavMenu,
    VueMarkdown,
    Comment
  },
  data () {
    return {
      article: [],
      love: true,
      loved: false, // 为 true 时表示文章已被用户收藏
      labels: []
    }
  },
  methods: {
    getArticle () {
      axios.get('http://localhost:8080/articles?id=' + this.$route.query.id).then(response => {
        this.article = response.data
        this.getTags()
        console.log(this.article)
        this.loveExist()
      }).catch(error => {
        console.log(error)
      })
    },
    // 收藏
    loveArticle () {
      let userId = window.localStorage.userId
      let currentDate = new Date()
      let loveTime = currentDate.toLocaleString('zh', { hour12: false })
      let loveDate = currentDate.toLocaleDateString()
      axios({
        method: 'post',
        url: 'http://localhost:8080/loveArticle',
        data: {
          user_id: userId,
          article_id: this.article.id,
          love_time: loveTime,
          love_date: loveDate
        }
      }).then(response => {
        console.log(response)
        if (response.data.message === '收藏成功！') {
          this.$message({
            message: response.data.message,
            type: 'success'
          })
          this.loved = true
          this.love = false
          this.getArticle()
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 取消收藏
    cancelLoveArticle () {
      let userId = window.localStorage.userId
      axios({
        method: 'post',
        url: 'http://localhost:8080/deleteLove',
        data: 'user_id=' + userId + '&article_id=' + this.article.id
      }).then(response => {
        console.log(response)
        if (response.data.message === '取消收藏！') {
          this.$message(response.data.message)
          this.loved = false
          this.love = true
          this.getArticle()
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 判断收藏关系是否存在，文章是否被收藏
    loveExist () {
      let userId = window.localStorage.userId
      axios({
        method: 'post',
        url: 'http://localhost:8080/selectLoveExist',
        data: `user_id=${userId}&article_id=${this.article.id}`
      }).then(response => {
        if (response.data === true) {
          this.loved = true
          this.love = false
        } else {
          this.loved = false
          this.love = true
        }
      }).catch(error => {
        console.log(error)
      })
    },
    getTags () {
      axios.get('http://localhost:8080/getLabel?articleId=' + this.article.id).then(response => {
        this.labels = response.data
      }).catch(error => {
        console.log(error)
      })
    }
  },
  mounted () {
    this.getArticle()
  },
  updated: function () {
    Prism.highlightAll()
  }
}
</script>

<style>
#articles {
  width: 80%;
  margin: 0 auto;
}
.article-content {
  margin-left: 2rem;
}
p.author-info {
  color: #909399;
}
span.action {
  line-height: 3.5rem;
  margin-left: 1rem;
  cursor: pointer;
  color: #409eff;
}
.tags {
  margin-bottom: 2rem;
  margin-top: 1rem;
}
.el-tag {
  margin-left: 0.6rem;
}
code {
  color: black;
  background: none;
  text-shadow: 0 1px white;
  font-family: Consolas, Monaco, "Andale Mono", "Ubuntu Mono", monospace;
  font-size: 1em;
  text-align: left;
  white-space: pre;
  word-spacing: normal;
  word-break: normal;
  word-wrap: normal;
  line-height: 1.5;
  -moz-tab-size: 4;
  -o-tab-size: 4;
  tab-size: 4;
  -webkit-hyphens: none;
  -ms-hyphens: none;
  hyphens: none;
}
</style>
