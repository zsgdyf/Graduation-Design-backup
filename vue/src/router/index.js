import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from '@/views/userList'
import Add from '@/views/add'
import Index from '@/views/index'
import MyNotes from '@/views/myNotes'
import Editor from '@/views/editor'
import Articles from '@/views/articles'
import UserInfo from '@/views/userInfo'
import MyLove from '@/views/myLove'

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
  path: '/user',
  component: UserInfo
}, {
  path: '/',
  redirect: '/index'
}, {
  path: '/editor',
  component: Editor
}, {
  path: '/myNotes',
  component: MyNotes
}, {
  path: '/articles',
  component: Articles
}, {
  path: '/myLove',
  component: MyLove
}]

export default new VueRouter({
  routes,
  // 浏览器后退时保持滚动条原有位置
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      console.log(savedPosition)
      setTimeout(() => {
        window.scrollTo(savedPosition.x, savedPosition.y)
      }, 270)
    }
  }
})
