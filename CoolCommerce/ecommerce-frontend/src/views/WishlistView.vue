<template>
  <v-container class="pa-14">
    <v-row>
      <v-col>
        <v-card>
          <v-data-table
              :headers="headers"
              :items="storeState.wishlist.items"
              hide-default-footer>
            <template v-slot:[`item.thumbnailImage.url`]="{ item }">
              <v-img :src="item.thumbnailImage.url" height="100px" width="100px" contain></v-img>
            </template>

            <template v-slot:[`item.price`]="{ item }">
              <span>{{numeral(item.price).format('$0,0.00')}}</span>
            </template>

            <template v-slot:[`item.delete`]="{ item }">
              <v-btn icon
                     small
                     @click="removeFromWishlist(item)">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </template>

          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import {inject} from 'inversify-props'
import {IStoreState} from '@/states/IStoreState'
import numeral from 'numeral'
import Product from '@/domain/Product'
import {DataTableHeader} from 'vuetify'

@Component({
             components: { }
           })
export default class WishlistView extends Vue {

  private numeral = numeral

  @inject()
  private storeState!: IStoreState

  private headers: DataTableHeader[] = [
    {
      text: 'Product',
      value: 'thumbnailImage.url',
      sortable: false,
      width: '200px'
    },
    {
      text: 'Name',
      value: 'name',
      sortable: false
    },
    {
      text: 'Price',
      value: 'price',
      sortable: false
    },
    {
      text: '',
      value: 'delete',
      align: 'center',
      width: '120px'
    },
  ]


  constructor() {
    super()
  }

  removeFromWishlist(product: Product): void{
    this.storeState.wishlist.toggleItem(product)
  }

}
</script>

<style scoped>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .8;
  position: absolute;
  width: 100%;
}

.v-card__title {
  word-break: break-word;
}
</style>
