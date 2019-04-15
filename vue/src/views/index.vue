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
            <h4 @click="toArticle(article.id)">{{article.title}}</h4>
          </a>
          <p class="author-info"><span>{{article.author}}</span> 于 <span>{{article.create_date}}</span></p>
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
export default {
  components: {
    NavMenu
  },
  data () {
    return {
      articles: [],
      labels: []
    }
  },
  methods: {
    getArticles () {
      axios.get('http://localhost:8080/popularArticles').then(response => {
        this.articles = response.data
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
      }).catche(error => {
        console.log(error)
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
<style scoped>
#index {
  width: 80%;
  margin: 0 auto;
}
.el-card.articles {
  margin: 0.8rem;
  width: 50rem;
}
.el-card.labels {
  width: 20%;
  margin: 0.8rem;
  height: 15rem;
}
.el-card.articles-content {
  margin-bottom: 0.5rem;
}
.author-info {
  color: #909399;
  font-size: 0.9rem;
}
h4 {
  cursor: pointer;
}
.el-tag {
  margin-left: 0.6rem;
  margin-bottom: 0.5rem;
}
</style>
