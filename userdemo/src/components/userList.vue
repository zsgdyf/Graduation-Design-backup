<template>
  <div>
    <h1>用户列表</h1>
    <router-link to="/add">新增</router-link>
    <el-table :data='personData'>
      <el-table-column
        prop='id'
        label='ID'
      ></el-table-column>
      <el-table-column
        prop='name'
        label='Name'
      ></el-table-column>
      <el-table-column
        prop='password'
        label='Password'
      ></el-table-column>
    </el-table>
  </div>
</template>

<script>
// Italic comment test
import axios from 'axios'
export default {
  data () {
    return {
      personData: ''
    }
  },
  methods: {
    getData () {
      axios.get('http://localhost:8080/selectAll').then(response => {
        this.personData = response.data
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    created () {
      this.getData()
    }
  }
}
</script>

<style scoped>
.el-table {
  width: 60%;
  margin: 0 auto;
}
</style>
