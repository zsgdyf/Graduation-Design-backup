<template>
  <div id="editor">
    <NavMenu></NavMenu>
    <div id="content">
      <el-row
        type="flex"
        class="row-bg"
        justify="center"
      >
        <SideNavMenu></SideNavMenu>
        <div class="editor-container">
          <div class="editor-box">
            <el-input
              class="article-title"
              v-model="article.title"
              placeholder="请输入笔记标题"
            ></el-input>
            <markdown-editor
              v-model="content_md"
              ref="markdownEditor"
              preview-class="markdown-body"
              :highlight="true"
              :configs="configs"
            ></markdown-editor>
            <el-button
              type="primary"
              @click="publishConfirm"
            >发布</el-button>
            <el-button
              type="primary"
              @click="updateConfirm"
            >更新</el-button>
          </div>
        </div>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import markdownEditor from 'vue-simplemde/src/markdown-editor'
import NavMenu from '@/components/NavMenu.vue'
import SideNavMenu from '@/components/SideNavMenu.vue'
import hljs from 'highlight.js'
window.hljs = hljs
export default {
  components: {
    markdownEditor,
    NavMenu,
    SideNavMenu
  },
  data () {
    return {
      article: {
        title: '',
        content: '',
        author: '',
        creat_time: '',
        creat_date: '',
        state: '',
        loveCount: 0
      },
      content_md: '',
      configs: {
        spellChecker: false,
        autosave: {
          enable: true,
          delay: 1000,
          uniqueId: 'autoSave'
        },
        placeholder: '请输入笔记内容'
      }
    }
  },
  methods: {
    publishConfirm () {
      this.$confirm('确认发布吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消，存入草稿箱',
        type: 'warning'
      }).then(() => {
        if (this.$confirm) {
          this.article.state = 'published'
          this.publish()
        }
      }).catch(() => {
        this.$message({
          message: '已存入草稿箱',
          type: 'info'
        })
        this.article.state = 'draft'
        this.publish()
      })
    },
    publish () {
      this.getArticleData()
      axios({
        method: 'post',
        url: 'http://localhost:8080/newArticle',
        data: {
          title: this.article.title,
          content_md: this.content_md,
          content: this.article.content,
          author: this.article.author,
          create_time: this.article.creat_time,
          create_date: this.article.creat_date,
          state: this.article.state,
          loveCount: this.article.loveCount
        }
      }).then(response => {
        if (response.data.state === 'published') {
          this.$message({
            message: '发布成功！',
            type: 'success'
          })
        } else if (response.data.state === 'draft') {
          this.$message({
            message: '已存入草稿箱',
            type: 'info'
          })
        }
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    getArticleData () {
      this.article.author = localStorage.userName
      this.article.content = this.simplemde.markdown(this.content_md)
      let currentDate = new Date()
      this.article.creat_time = currentDate.toLocaleString('zh', { hour12: false })
      this.article.creat_date = currentDate.toLocaleDateString()
    },
    getEditValue () {
      axios.get(`http://localhost:8080/articles?id=${this.$route.query.id}`).then(response => {
        this.article.title = response.data.title
        this.simplemde.value(response.data.content_md)
      }).catch(error => {
        console.log(error)
      })
    },
    updateConfirm () {
      this.$confirm('确认发布编辑后的笔记吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.$confirm) {
          this.article.state = 'published'
          this.update()
        }
      }).catch(() => {
        this.$message({
          message: '已取消',
          type: 'info'
        })
        // this.article.state = 'draft'
        this.update()
      })
    },
    update () {
      this.getArticleData()
      axios({
        method: 'post',
        url: 'http://localhost:8080/updateArticle',
        data: {
          id: this.$route.query.id,
          title: this.article.title,
          content_md: this.content_md,
          content: this.article.content,
          author: this.article.author,
          create_time: this.article.creat_time,
          create_date: this.article.creat_date,
          state: this.article.state,
          loveCount: this.article.loveCount
        }
      }).then(response => {
        if (response.data.state === 'published') {
          this.$message({
            message: '发布成功！',
            type: 'success'
          })
        } else if (response.data.state === 'draft') {
          this.$message({
            message: '已存入草稿箱',
            type: 'info'
          })
        }
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    }
  },
  computed: {
    simplemde () {
      return this.$refs.markdownEditor.simplemde
    }
  },
  mounted () {
    console.log(this.simplemde)
    if (this.$route.query.id) {
      this.getEditValue()
    }
  }
  // beforeRouteLeave (to, from, next) {
  //   this.$confirm('当前正在编辑的笔记未保存，确认离开？', '提示', {
  //     confirmButtonText: '确定',
  //     cancelButtonText: '取消',
  //     type: 'warning'
  //   }).then(() => {
  //     if (this.$confirm) {
  //       next()
  //     } else {
  //       next(false)
  //     }
  //   })
  // }
}
</script>

<style>
@import "simplemde/dist/simplemde.min.css";
@import "highlight.js/styles/atom-one-light.css";
/* Highlight theme list: https://github.com/isagalaev/highlight.js/tree/master/src/styles */
@import "github-markdown-css";
#editor {
  width: 80%;
  margin: 0 auto;
  margin-bottom: 1rem;
  height: 100%;
}
.editor-container {
  width: 80%;
}
.editor-box {
  padding-left: 2rem;
}
#content {
  margin-top: 2rem;
}
.article-title {
  padding-bottom: 1rem;
}
/*修改代码块背景色及字体颜色*/
/* .theme .editor-preview-side pre,
.theme .editor-preview pre {
  color: #abb2bf !important;
  background: #282c34 !important;
} */
</style>
