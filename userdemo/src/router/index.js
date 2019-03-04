import Vue from 'vue'
import Router from 'vue-router'
import UserList from '@/components/userList'
import Add from '@/components/add'
import Index from '@/components/index'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    redirect: '/index'
  },
  {
    path: '/index',
    name: 'index',
    component: Index
  },
  {
    path: '/add',
    name: 'add',
    component: Add
  },
  {
    path: '/users',
    name: 'userList',
    component: UserList
  }]
})
