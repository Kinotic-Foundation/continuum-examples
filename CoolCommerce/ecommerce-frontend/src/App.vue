<template>
  <v-app>
    <v-app-bar
      app
      clipped-left
      color="primary"
      dark>
      <div class="d-flex align-center">
        <h2>Cool Commerce</h2>
      </div>

      <v-spacer></v-spacer>

      <v-btn to="/cart" icon>
        <v-badge
            v-if="storeState.cart.itemCount"
            :content="storeState.cart.itemCount"
            color="error"
            dense
            left>
        </v-badge>
        <v-icon>mdi-cart</v-icon>
      </v-btn>

      <v-btn to="/wishlist" icon>
        <v-badge
            v-if="storeState.wishlist.itemCount"
            :content="storeState.wishlist.itemCount"
            color="error"
            dense
            left>
        </v-badge>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <template v-slot:extension>
        <v-tabs background-color="white"
                centered
                light>
          <v-tab v-for="category in storeState.categories"
                 :key="category.id"
                 :to="getLink(category)">{{category.name}}</v-tab>
        </v-tabs>

      </template>

    </v-app-bar>


    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from 'vue'
import { Component } from 'vue-property-decorator'
import { inject } from 'inversify-props'
import { IEventBus } from '@kinotic-foundation/continuum-js'
import {IStoreService} from '@/services/IStoreService'
import Category from '@/domain/Category'
import {IStoreState} from '@/states/IStoreState'


@Component({
             components: { }
           })
export default class App extends Vue {

  @inject()
  public eventBus!: IEventBus

  @inject()
  private storeService!: IStoreService

  @inject()
  private storeState!: IStoreState

  private loading = false

  public async beforeMount() {
    await this.eventBus.connect('ws://127.0.0.1:58503/v1', 'guest', 'guest')
  }

  public async mounted() {
    this.loading = true

    let data = await this.storeService.getAllCategories()
    this.storeState.categories.push(...data)

    this.loading = false
  }

  public beforeDestroy() {
    this.eventBus.disconnect()
  }

  private getLink(category: Category): string{
    return `/category/${category.id}`
  }

}

</script>
