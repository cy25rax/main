import Vue from 'vue'
import App from './Blog.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#blog')