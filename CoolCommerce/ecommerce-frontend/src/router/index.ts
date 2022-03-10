import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import ProductListView from '@/views/ProductListView.vue'
import ProductView from '@/views/ProductView.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  { // default route
    path: '/',
    redirect: '/productList/1'
  },
  {
    path: '/productList/:categoryId',
    props: true,
    component: ProductListView
  },
  {
    path: '/product/:productId',
    props: true,
    component: ProductView
  }
]

const router = new VueRouter({
  routes
})

export default router
