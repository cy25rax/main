import Vue from 'vue'
import BlogDetailsApp from './BlogDetailsApp.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(BlogDetailsApp),
}).$mount('#blog_details_app')
