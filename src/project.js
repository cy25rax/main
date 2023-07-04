import Vue from 'vue'
import App from './ProjectApp.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#project_app')
