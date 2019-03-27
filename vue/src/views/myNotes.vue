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
        <el-timeline>
          <el-timeline-item
            v-for="(article,index) in articles"
            :key="index"
            :timestamp="article.create_date"
            placement="top"
          >
            <el-card>
              <h4>{{article.title}}</h4>
              <p>{{article.author}} 提交于 {{article.create_time}}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
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
      articles: []
    }
  },
  methods: {
    getData () {
      axios.get('http://localhost:8080/myNotes').then(response => {
        this.articles = response.data
        console.log(this.articles)
      })
    }
  },
  created () {
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
.el-timeline {
  width: 80%;
}
</style>
