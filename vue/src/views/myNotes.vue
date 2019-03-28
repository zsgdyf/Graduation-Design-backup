<template>
  <div id="myNotes">
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
            <el-radio-group v-model="reverse">
              <el-radio :label="false">正序</el-radio>
              <el-radio :label="true">倒序</el-radio>
            </el-radio-group>
          </div>
          <el-timeline :reverse="reverse">
            <el-timeline-item
              v-for="(article,index) in articles"
              :key="index"
              :timestamp="article.create_date"
              placement="top"
            >
              <el-card>
                <el-row
                  type="flex"
                  class="row-bg"
                >
                  <h4 @click="toArticles(article.id)">{{article.id}}# {{article.title}}</h4>
                  <span class="action">编辑</span>
                  <span class="action">删除</span>
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
      author: '',
      reverse: false
    }
  },
  methods: {
    getData () {
      axios.get('http://localhost:8080/myNotes?author=' + this.author).then(response => {
        this.articles = response.data
        console.log(this.articles)
      })
    },
    toArticles (articleId) {
      this.$router.push({
        path: '/articles',
        query: {
          id: articleId
        }
      })
    }
  },
  created () {
    this.author = localStorage.userName
    this.getData()
  }
}
</script>

<style scoped>
#myNotes {
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
</style>
