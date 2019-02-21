import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Router from 'vue-router'
import UserList from '@/components/userList'
import Add from '@/components/add'

Vue.use(Router)
Vue.use(ElementUI)

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
