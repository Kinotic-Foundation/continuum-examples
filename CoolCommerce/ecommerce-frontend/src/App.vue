<template>
  <v-app>
    <v-navigation-drawer app clipped>

      <v-list class="navigation-drawer__list">
        <v-list-item v-for="category in categories"
                     :key="category.id"
                     :to="getLink(category)">

          <v-list-item-content>
            <v-list-item-title>{{category.name}}</v-list-item-title>
          </v-list-item-content>

        </v-list-item>
      </v-list>

    </v-navigation-drawer>
    <v-app-bar
      app
      clipped-left
      color="primary"
      dark>
      <div class="d-flex align-center">
        <h2>Cool Commerce</h2>
      </div>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>fa-cart-shopping</v-icon>
      </v-btn>
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
import {IStoreService} from '@/services/StoreService'
import Category from '@/domain/Category'


@Component({
             components: { }
           })
export default class App extends Vue {

  @inject()
  public eventBus!: IEventBus

  @inject()
  private storeService!: IStoreService

  private categories: Category[] = []

  private loading = false

  public async beforeMount() {
    await this.eventBus.connect('ws://localhost:58503/v1', 'super', 'w3mak3th1sr0ck1nr0ll')
  }

  public async mounted() {
    this.loading = true

    let data = await this.storeService.getAllCategories()
    this.categories.push(...data)

    this.loading = false
  }

  public beforeDestroy() {
    this.eventBus.disconnect()
  }

  private getLink(category: Category): string{
    return `/productList/${category.id}`
  }

}

</script>
