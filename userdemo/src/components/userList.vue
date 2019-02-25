<template>
  <div>
    <NavMenu></NavMenu>
    <el-table :data="userData">
      <el-table-column
        prop='id'
        label='#'
      ></el-table-column>
      <el-table-column
        prop='name'
        label='用户名'
      ></el-table-column>
      <el-table-column
        prop='password'
        label='密码'
      ></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="deleteConfirm(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// Italic comment test
import axios from 'axios'
import NavMenu from './NavMenu.vue'
export default {
  components: {
    NavMenu
  },
  data () {
    return {
      userData: ['']
    }
  },
  methods: {
    getData () {
      axios.get('http://localhost:8080/selectAll').then(response => {
        this.userData = response.data
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    deleteConfirm (index) {
      this.$confirm('是否删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.$confirm) {
          this.deleted(index)
        }
      })
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
    deleted (index) {
      console.log(index)
      axios({
        method: 'post',
        url: 'http://localhost:8080/delete',
        data: '&id=' + index
      }).then(response => {
        console.log(response)
        this.getData()
      }).catch(error => {
        console.log(error)
      })
    }
  },

  created () {
    this.getData()
  }
}
</script>

<style scoped>
.el-table {
  width: 600px;
  margin: 0 auto;
}
</style>
