<template>
  <div id="articles">
    <NavMenu></NavMenu>
    <div class="article-content">
      <h1>{{article.title}}</h1>
      <p class="author-info">作者：{{article.author}}&nbsp;&nbsp;
        发布于：{{article.create_time}}
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
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import VueMarkdown from 'vue-markdown'
import Prism from 'prismjs'
import NavMenu from '@/components/NavMenu.vue'
import SideNavMenu from '@/components/SideNavMenu.vue'
export default {
  components: {
    NavMenu,
    SideNavMenu,
    VueMarkdown
  },
  data () {
    return {
      article: [],
      love: true,
      loved: false
    }
  },
  methods: {
    getArticle () {
      axios.get('http://localhost:8080/articles?id=' + this.$route.query.id).then(response => {
        this.article = response.data
        console.log(this.article)
      }).catch(error => {
        console.log(error)
      })
    },
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
        this.loved = true
        this.love = false
      }).catch(error => {
        console.log(error)
      })
    },
    cancelLoveArticle () {
      let userId = window.localStorage.userId
      axios({
        method: 'post',
        url: 'http://localhost:8080/deleteLove',
        data: '&user_id=' + userId + '&article_id=' + this.article.id
      }).then(response => {
        console.log(response)
        this.loved = false
        this.love = true
      }).catch(error => {
        console.log(error)
      })
    }
  },
  created () {
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
