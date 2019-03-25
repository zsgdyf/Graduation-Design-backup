import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from '@/views/userList'
import Add from '@/views/add'
import Index from '@/views/index'
import MyNotes from '@/views/myNotes'
import Editor from '@/views/editor'

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
}, {
  path: '/editor',
  component: Editor
}, {
  path: '/myNotes',
  component: MyNotes
}]
export default new VueRouter({
  routes
})
