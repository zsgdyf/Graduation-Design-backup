<template>
  <div id="index">
    <NavMenu></NavMenu>
    <el-row
      type="flex"
      class="row-bg"
      justify="center"
    >
      <el-card
        class="articles"
        shadow="never"
      >
        <p>热门推荐</p>
        <el-card
          class="articles-content"
          v-for="(article,index) in articles"
          :key="index"
          shadow="never"
          :body-style="{padding: '10px'}"
        >
          <a target="_blank">
            <h3 @click="toArticle(article.id)">{{article.title}}</h3>
          </a>
          <p class="author-info">
            <span>{{article.author}}</span>&nbsp;
            发布于&nbsp;
            <span>{{article.create_date}}</span>&nbsp;
            <span>收藏数：<span>{{article.love_count}}</span></span>
          </p>
          <p class="content-part">
            <vue-markdown :source="contentPart[index]"></vue-markdown>
            <span
              class="index-action"
              @click="toArticle(article.id)"
            >查看全文>></span>
          </p>
        </el-card>
      </el-card>
      <el-card
        class="labels"
        shadow="never"
      >
        <p>热门标签</p>
        <el-tag
          :key="label.id"
          v-for="label in labels"
        >{{label.content}}</el-tag>
      </el-card>
    </el-row>
  </div>
</template>
<script>
import axios from 'axios'
import NavMenu from '@/components/NavMenu.vue'
import VueMarkdown from 'vue-markdown'
export default {
  components: {
    NavMenu,
    VueMarkdown
  },
  data () {
    return {
      articles: [],
      labels: [],
      contentPart: []
    }
  },
  methods: {
    getArticles () {
      axios.get('http://localhost:8080/popularArticles').then(response => {
        this.articles = response.data
        this.getContentPart()
        console.log(this.contentPart)
      }).catch(error => {
        console.log(error)
      })
    },
    toArticle (articleId) {
      this.$router.push({
        path: '/articles',
        query: {
          id: articleId
        }
      })
    },
    getLabels () {
      axios.get('http://localhost:8080/popularLabels').then(response => {
        this.labels = response.data
      }).catch(error => {
        console.log(error)
      })
    },
    getContentPart () {
      this.articles.forEach((article, index) => {
        if (article.content_md.length <= 50) {
          this.contentPart[index] = article.content_md
        } else {
          this.contentPart[index] = article.content_md.slice(0, 51)
        }
      })
    }
  },
  created () {
    this.getArticles()
  },
  mounted () {
    this.getLabels()
  }
}
</script>
<style lang="scss" scoped>
#index {
  width: 80%;
  margin: 0 auto;
}
.el-card {
  &.articles {
    margin: 0.8rem;
    width: 50rem;
  }
  &.labels {
    width: 20%;
    margin: 0.8rem;
    height: 15rem;
  }
  &.articles-content {
    margin-bottom: 0.5rem;
  }
}
.author-info {
  color: #909399;
  font-size: 0.9rem;
}
h3 {
  cursor: pointer;
}
.el-tag {
  margin-left: 0.6rem;
  margin-bottom: 0.5rem;
}
span {
  &.index-action {
    cursor: pointer;
    color: #409eff;
    &:hover {
      text-decoration: underline;
    }
  }
}
</style>
