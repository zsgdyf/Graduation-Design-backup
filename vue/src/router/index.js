import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from '@/components/userList'
import Add from '@/components/add'
import Index from '@/components/index'

Vue.use(VueRouter)

const routes = [{
  path: '/index',
  component: Index
}, {
  path: '/add',
  component: Add
}, {
  path: '/users',
  component: UserList
}, {
  path: '/',
  redirect: '/index'
}]
export default new VueRouter({
  routes
})
