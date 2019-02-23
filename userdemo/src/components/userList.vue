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
        <el-button>
          删除
        </el-button>
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
