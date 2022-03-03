<template>
  <v-app>
    <v-app-bar
      app
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


@Component({
             components: { }
           })
export default class App extends Vue {

  @inject()
  public eventBus!: IEventBus

  public async mounted() {
    await this.eventBus.connect('ws://localhost:58503/v1', 'super', 'w3mak3th1sr0ck1nr0ll')
  }

  public beforeDestroy() {
    this.eventBus.disconnect()
  }

}

</script>
