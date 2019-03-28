<template>
  <div id="articles">
    <NavMenu></NavMenu>
    <div class="article-content">
      <h1>{{article.title}}</h1>
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
      article: []
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
