import Vue from 'vue';
import vueResource from 'vue-resource';
import jQuery from 'jquery';
import App from './App.vue';
import router from './router';
import 'bootstrap/dist/css/bootstrap.css';

global.jQuery = jQuery;
require('bootstrap');

Vue.config.productionTip = false;

Vue.use(vueResource);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
