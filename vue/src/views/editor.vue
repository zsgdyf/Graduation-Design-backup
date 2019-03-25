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
        <markdown-editor
          v-model="content"
          ref="markdownEditor"
          preview-class="markdown-body"
          :highlight="true"
        ></markdown-editor>
      </el-row>
    </div>
  </div>
</template>

<script>
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
      content: '',
      configs: {
        spellChecker: false
      }
    }
  },
  computed: {
    simplemde () {
      return this.$refs.markdownEditor.simplemde
    }
  },
  mounted () {
    console.log(this.simplemde)
    // this.simplemde.togglePreview()
  }
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
}
.markdown-editor {
  width: 80%;
  padding-left: 40px;
}
#content {
  margin-top: 2rem;
}
/*修改代码块背景色及字体颜色*/
/* .theme .editor-preview-side pre,
.theme .editor-preview pre {
  color: #abb2bf !important;
  background: #282c34 !important;
} */
</style>
