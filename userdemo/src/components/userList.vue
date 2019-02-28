<template>
  <div>
    <NavMenu></NavMenu>
    <el-table :data="tempList">
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
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20, 50, 100]"
      :page-size="pageSize"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
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
      userData: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tempList: []
    }
  },
  methods: {
    getData () {
      axios.get('http://localhost:8080/selectAll').then(response => {
        this.userData = response.data
        this.total = this.userData.length
        if (this.total > this.pageSize) {
          for (let i = 0; i < this.pageSize; ++i) {
            this.tempList.push(this.userData[i])
          }
        } else {
          this.tempList = this.userData
        }
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
      })
        .then(() => {
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
        this.tempList = [] // 删除一个数据之后将 tempList 重新制空，不然会出现两个重复的
        this.getData()
      }).catch(error => {
        console.log(error)
      })
    },
    sizeChange (pageSize) {
      this.pageSize = pageSize
      this.currentChange(this.currentPage)
    },
    currentChange (currentPage) {
      this.currentPage = currentPage
      this.currentPageChange(this.userData, currentPage)
    },
    currentPageChange (list, currentPage) {
      let from = (currentPage - 1) * this.pageSize
      let to = currentPage * this.pageSize
      this.tempList = []
      for (; from < to; ++from) {
        if (list[from]) {
          this.tempList.push(list[from])
        }
      }
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
