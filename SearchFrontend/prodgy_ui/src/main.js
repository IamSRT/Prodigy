import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import store from './store/store'
import BootStrapVue from  'bootstrap-vue'
import ComponentsPlugin from './plugins/components.plugin';
import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'
Vue.use(BootStrapVue);
Vue.use(ComponentsPlugin);
Vue.use(VueMaterial);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
