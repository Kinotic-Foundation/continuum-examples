import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import CategoryView from '@/views/CategoryView.vue'
import ProductView from '@/views/ProductView.vue'
import CartView from '@/views/CartView.vue'
import WishlistView from '@/views/WishlistView.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  { // default route
    path: '/',
    redirect: '/category/1'
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
  },
  {
    path: '/cart',
    component: CartView
  },
  {
    path: '/wishlist',
    component: WishlistView
  }
]

const router = new VueRouter({
  routes
})

export default router
