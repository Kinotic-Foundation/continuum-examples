<template>
  <v-container class="pa-14" fluid>
    <v-row>
      <v-col cols="2" xs="12">
        <v-card outlined>
          <v-card-title>Categories</v-card-title>
          <v-divider></v-divider>
          <template>
            <v-list dense link>
              <v-list-item-group
                  color="primary">
                <v-list-item
                    v-for="(item, i) in storeState.categories"
                    :key="i"
                    :to="'/category/' + item.id">
                  <v-list-item-content>
                    <v-list-item-title v-text="item.name"></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </template>
          <v-divider></v-divider>
          <v-card-title>Price</v-card-title>
          <v-range-slider
              v-model="range"
              :max="max"
              :min="min"
              :height="10"
              class="align-center"
              dense
          ></v-range-slider>
          <v-row class="pa-2" dense>
            <v-col cols="12" sm="5">
              <v-text-field
                  :value="range[0]"
                  label="Min"
                  outlined
                  dense
                  @change="$set(range, 0, $event)"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="2">
              <p class="pt-2 text-center">TO</p>
            </v-col>
            <v-col cols="12" sm="5">
              <v-text-field
                  :value="range[1]"
                  label="Max"
                  outlined
                  dense
                  @change="$set(range, 1, $event)"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-divider></v-divider>
          <v-card-title class="pb-0">Customer Rating</v-card-title>
          <v-container class="pt-0" fluid>
            <v-checkbox append-icon="mdi-star" label="4 & above" hide-details dense></v-checkbox>
            <v-checkbox append-icon="mdi-star" label="3 & above" hide-details dense></v-checkbox>
            <v-checkbox append-icon="mdi-star" label="2 & above" hide-details dense></v-checkbox>
            <v-checkbox append-icon="mdi-star" label="1 & above" hide-details dense></v-checkbox>
          </v-container>
        </v-card>
      </v-col>
      <v-col cols="10" xs="12">
        <v-row>
          <v-col
              v-for="product in products"
              :key="product.id"
              cols="3">
            <v-hover v-slot:default="{ hover }">
              <v-card width="300px">
                <v-img
                    :src="product.thumbnailImage.url"
                    class="white--text align-end"
                    height="200px">
                  <v-expand-transition>
                    <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;">
                      <v-btn v-if="hover" :to="'/product/' + product.id" class="" outlined>VIEW</v-btn>
                    </div>

                  </v-expand-transition>
                  <v-card-title v-text="product.name"></v-card-title>
                </v-img>
                <v-card-actions class="ma-3">
                  <span class="price">{{numeral(product.price).format('$0,0.00')}}</span>
                  <v-spacer></v-spacer>
                  <v-rating
                      v-model="product.rating"
                      background-color="orange lighten-3"
                      color="orange"
                      dense
                      readonly
                      half-increments
                      size="18">
                  </v-rating>
                </v-card-actions>
                <v-divider class="mx-3"></v-divider>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-tooltip bottom>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn icon
                             v-bind="attrs"
                             v-on="on"
                             @click="addToCart(product)">
                        <v-icon>mdi-cart</v-icon>
                      </v-btn>
                    </template>
                    <span>Add to cart</span>
                  </v-tooltip>

                  <v-tooltip bottom>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn icon
                             v-bind="attrs"
                             v-on="on"
                             :class="[wishlistContainsProduct(product) ? 'selected' : '']"
                             @click="addToWishlist(product)">
                        <v-icon>mdi-heart</v-icon>
                      </v-btn>
                    </template>
                    <span>Add to wishlist</span>
                  </v-tooltip>

                  <ShareDialogButton :link-text="'/product/' + product.id"></ShareDialogButton>

                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Prop, Vue, Watch} from 'vue-property-decorator';
import {inject} from 'inversify-props'
import {STORE_SERVICE} from '@/services/IStoreService'
import Product from '@/domain/Product'
import ShareDialogButton from '@/components/ShareDialogButton.vue'
import numeral from 'numeral'
import {IStoreState} from '@/states/IStoreState'

@Component({
             components: {
               ShareDialogButton
             },
           })
export default class CategoryView extends Vue {

  private numeral = numeral

  @Prop({type: String, required: true, default: null})
  public categoryId!: string

  @inject()
  private storeState!: IStoreState

  private loading = false

  private products: Product[] = []

  private range = [0, 2000]
  private min = 0
  private max = 2000

  public async mounted() {
    await this.loadData(parseInt(this.categoryId))
  }

  @Watch('categoryId')
  public async watchPagination(value: any, oldValue: any) {
    await this.loadData(parseInt(value))
  }

  private async loadData(categoryId: number){
    this.loading = true
    this.products.splice(0, this.products.length)
    console.log(`Loading Data for Category Id: ${this.categoryId}`)
    let data = await STORE_SERVICE.getAllProductsForCategoryId(parseInt(this.categoryId))
    this.products.push(...data)

    this.loading = false
  }

  private addToCart(product: Product){
    this.storeState.cart.addItem(product, 1)
  }

  private addToWishlist(product: Product){
    this.storeState.wishlist.toggleItem(product)
  }

  private wishlistContainsProduct(product: Product): boolean{
    return this.storeState.wishlist.containsProduct(product)
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

.price {
  color: #f44336;
  font-size: 16px;
  font-weight: 500;
}

.selected {
  color: #ff5252 !important;
}

</style>
