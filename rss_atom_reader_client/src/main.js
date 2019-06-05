import Vue from 'vue';
import jQuery from 'jquery';
import App from './App.vue';
import router from './router';
import 'bootstrap/dist/css/bootstrap.css';

global.jQuery = jQuery;
require('bootstrap');

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
