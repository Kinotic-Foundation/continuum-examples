<template>
  <v-container class="pa-14">
    <v-row>
      <v-col>
        <v-card>
          <v-data-table
              :headers="headers"
              :items="storeState.cart.items"
              item-key="product.id"
              hide-default-footer>
            <template v-slot:[`item.thumbnail`]="{ item }">
              <v-img :src="item.thumbnail" height="100px" width="100px" contain></v-img>
            </template>

            <template v-slot:[`item.product.price`]="{ item }">
              <span>{{numeral(item.product.price).format('$0,0.00')}}</span>
            </template>

            <template v-slot:[`item.quantity`]="{ item }">
              <v-btn icon
                     x-small
                     :disabled="item.quantity === 1"
                     @click="decrementCartItem(item.product)">
                <v-icon>mdi-minus</v-icon>
              </v-btn>

              <div class="mx-2" style="width: 30px; display:inline-block; text-align: center">
                {{item.quantity}}
              </div>

              <v-btn icon
                     x-small
                     @click="incrementCartItem(item.product)">
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </template>

            <template v-slot:[`item.total`]="{ item }">
              <span>{{numeral(item.total).format('$0,0.00')}}</span>
            </template>

            <template v-slot:[`item.delete`]="{ item }">
              <v-btn icon
                     small
                     @click="removeCartItem(item.product)">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </template>

          </v-data-table>
          <v-card-actions>
            <v-spacer></v-spacer>
            <span class="text-subtitle-1">Total:&nbsp;</span><span class="price">{{numeral(storeState.cart.total).format('$0,0.00')}}</span>
            <v-btn to="/checkout" class="ml-6" color="primary">Checkout</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import {inject} from 'inversify-props'
import {IStoreState} from '@/states/IStoreState'
import {DataTableHeader} from 'vuetify'
import Product from '@/domain/Product'
import numeral from 'numeral'

@Component({
             components: { }
           })
export default class CartView extends Vue {

  private numeral = numeral

  @inject()
  private storeState!: IStoreState

  private headers: DataTableHeader[] = [
    {
      text: 'Product',
      value: 'thumbnail',
      sortable: false,
      width: '200px'
    },
    {
      text: 'Name',
      value: 'product.name',
      sortable: false
    },
    {
      text: 'Price',
      value: 'product.price',
      sortable: false
    },
    {
      text: 'Quantity',
      value: 'quantity',
      sortable: false,
      align: 'center',
      width: '150px'
    },
    {
      text: 'Total',
      value: 'total',
      sortable: false,
      align: 'end',
      width: '120px'
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

  incrementCartItem(product: Product): void{
    this.storeState.cart.addItem(product, 1);
  }

  decrementCartItem(product: Product): void {
    this.storeState.cart.decrementItem(product, 1)
  }

  removeCartItem(product: Product): void {
    this.storeState.cart.removeItem(product)
  }

}
</script>

<style scoped>
.price {
  color: #f44336;
  font-weight: bold;
  display:inline-block
}
</style>
