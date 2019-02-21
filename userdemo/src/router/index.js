import Vue from 'vue'
import Router from 'vue-router'
import UserList from '@/components/userList'
import Add from '@/components/add'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/users',
      name: 'userList',
      component: UserList
    },
    {
      path: '/add',
      name: 'Add',
      component: Add
    }
  ]
})
