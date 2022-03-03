import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import ProductListView from '@/views/ProductListView.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  { // default route
    path: '/',
    redirect: '/productList/1'
  },
  {
    path: '/productList/:categoryId',
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: ProductListView
  }
]

const router = new VueRouter({
  routes
})

export default router
