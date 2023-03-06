import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import CategoryView from '@/views/CategoryView.vue'
import ProductView from '@/views/ProductView.vue'
import CartView from '@/views/CartView.vue'
import WishlistView from '@/views/WishlistView.vue'
import CheckoutView from '@/views/CheckoutView.vue'
import ThankYouView from "@/views/ThankYouView.vue";

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
    path: '/checkout',
    component: CheckoutView
  },
  {
    path: '/wishlist',
    component: WishlistView
  },
  {
    path: '/thank_you/:confirmationId',
    props: true,
    component: ThankYouView
  },
]

const router = new VueRouter({
  routes
})

export default router
