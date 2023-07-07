import Vue from 'vue'
import App from './projectDetailsApp.vue'
import store from './store'


Vue.config.productionTip = false

new Vue({
  store,
  render: h => h(App),
}).$mount('#project_details_app')