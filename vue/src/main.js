// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

// import AV from 'leancloud-storage'
// const APP_ID = 'xxxxx'
// const APP_KEY = 'xxxxxx'
// AV.init({
//   appId: APP_ID,
//   appKey: APP_KEY
// })

Vue.use(ElementUI)

Vue.config.devtools = true

Vue.config.productionTip = false
axios.defaults.withCredential = true

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {
    App
  },
  template: '<App/>'
})
