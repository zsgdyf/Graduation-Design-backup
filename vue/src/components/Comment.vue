<template>
  <div id="comment">
    <div id="comment-input">
      <el-input
        type="textarea"
        :rows="3"
        placeholder="请输入评论"
        v-model="comment"
      >
      </el-input>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      comment: ''
    }
  }
}
</script>

<style lang="scss">
#comment {
  margin: {
    left: 2rem;
    bottom: 2rem;
  }
  .el-textarea__inner {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
  }
}
</style>
