<template slot-scope="scope">
  <el-button
    type="text"
    @click="deleteConfirm"
  >删除</el-button>
</template>
<script>
import axios from 'axios'
export default {
  props: {
    userId: String
  },
  methods: {
    deleteConfirm () {
      this.$confirm('是否删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(this.deleted(parseInt(this.userId)))
        .then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消删除'
          })
        })
    },
    deleted (id) {
      console.log(id)
      axios({
        method: 'post',
        url: 'http://localhost:8080/user/deleteUser',
        data: '&id=' + id
      }).then(response => {
        console.log(response)
        this.$parent.getData()
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>
