import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import CategoryView from '@/views/CategoryView.vue'
import ProductView from '@/views/ProductView.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  { // default route
    path: '/',
    redirect: '/productList/1'
  },
  {
    path: '/category/:categoryId',
    props: true,
    component: CategoryView
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
