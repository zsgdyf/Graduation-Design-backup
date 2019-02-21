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
      <!-- <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Age</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
      <tr
        v-for='item in personData'
        :key='item.id'
      >
        <td>{{item.id}}</td>
        <td>{{item.name}}</td>
        <td>{{item.password}}</td>
        <td>{{item.age}}</td>
        <td></td>
        <td @click="avoidDelete(item.id)">
          <button>删除</button>
        </td>
      </tr> -->
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
    avoidDelete (id) {
      var deleteConfirm = confirm('是否删除')
      if (deleteConfirm) {
        axios({
          method: 'post',
          url: 'http://localhost:8080/delete',
          data: '&id=' + id
        }).then(response => {
          console.log(response)
          this.getData()
        }).catch(error => {
          console.log(error)
        })
      }
    }
  },
  created () {
    this.getData()
  }
}
</script>

<style scoped>
table {
  border-collapse: collapse;
  margin: auto;
  font-size: 24px;
}
td,
th {
  border: 1px solid #000000;
}
</style>
