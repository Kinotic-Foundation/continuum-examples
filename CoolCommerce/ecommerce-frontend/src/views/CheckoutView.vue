<template>
  <v-container fluid fill-height>
    <v-row justify-center>
      <v-col>
        <v-card>
          <div>
            Some Content
          </div>
          <v-btn color="primary" flat @click="submit">Submit</v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import {inject} from 'inversify-props'
import {IStoreService} from '@/services/IStoreService'
import {IStoreState} from '@/states/IStoreState'
import numeral from 'numeral'

@Component({
             components: { }
           })
export default class CheckoutView extends Vue {

  private numeral = numeral

  @inject()
  private storeService!: IStoreService

  @inject()
  private storeState!: IStoreState

  constructor() {
    super()
  }

}
</script>

<style scoped>

</style>
