<template>
  <div id="myLove">
    <NavMenu></NavMenu>
    <div id="content">
      <el-row
        type="flex"
        class="row-bg"
        justify="center"
      >
        <SideNavMenu></SideNavMenu>
        <div id="timeline">
          <div class="radio">
            <span>按收藏时间：</span>
            <el-radio-group v-model="reverse">
              <el-radio :label="false">正序</el-radio>
              <el-radio :label="true">倒序</el-radio>
            </el-radio-group>
          </div>
          <el-timeline :reverse="reverse">
            <el-timeline-item
              v-for="(article,index) in articles"
              :key="index"
              :timestamp="loveArticle[index].love_date"
              placement="top"
            >
              <el-card>
                <el-row
                  type="flex"
                  class="row-bg"
                >
                  <h4 @click="toArticles(article.id)">{{article.id}}# {{article.title}}
                  </h4>
                  <span
                    class="action"
                    @click="cancelLoveArticle(article.id)"
                  >取消收藏</span>
                </el-row>
                <p>{{article.author}} 提交于 {{article.create_time}}</p>
              </el-card>
            </el-timeline-item>
          </el-timeline>
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
      articles: [],
      loveArticle: [],
      reverse: false
    }
  },
  methods: {
    getData () {
      let userId = window.localStorage.userId
      axios.get('http://localhost:8080/getLoveArticle?user_id=' + userId).then(response => {
        this.articles = response.data
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    toArticles (articleId) {
      this.$router.push({
        path: '/articles',
        query: {
          id: articleId
        }
      })
    },
    cancelLoveArticle (articleId) {
      let userId = window.localStorage.userId
      axios({
        method: 'post',
        url: 'http://localhost:8080/deleteLove',
        data: 'user_id=' + userId + '&article_id=' + articleId
      }).then(response => {
        console.log(response)
        if (response.data.message === '取消收藏！') {
          this.$message(response.data.message)
        }
        this.getData()
      }).catch(error => {
        console.log(error)
      })
    },
    getUserLove () {
      let userId = window.localStorage.userId
      axios.get(`http://localhost:8080/selectUserLove?user_id=${userId}`).then(response => {
        this.loveArticle = response.data
      }).catch(error => {
        console.log(error)
      })
    }
  },
  created () {
    this.getData()
  },
  mounted () {
    this.getUserLove()
  }
}
</script>

<style scoped>
#myLove {
  width: 80%;
  margin: 0 auto;
}
#content {
  margin-top: 2rem;
}
#timeline {
  width: 80%;
}
.radio {
  margin-left: 38px;
  margin-bottom: 0.5rem;
}
h4 {
  cursor: pointer;
  width: 85%;
}
span.action {
  line-height: 3.5rem;
  margin-left: 1rem;
  cursor: pointer;
  color: #409eff;
}
span.article-state {
  font-weight: normal;
  color: #909399;
}
</style>
