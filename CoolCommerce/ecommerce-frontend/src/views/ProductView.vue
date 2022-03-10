<template>
  <v-container class="pa-14">
    <v-row>
      <v-col md="5" sm="5" xs="12">
        <v-img
            :src="prod.image.url"
            class="white--text align-end"
            contain
            height="400px">
          <template v-slot:placeholder>
            <v-row
                class="fill-height ma-0"
                align="center"
                justify="center">
              <v-progress-circular
                  indeterminate
                  color="grey lighten-5"
              ></v-progress-circular>
            </v-row>
          </template>
        </v-img>
      </v-col>
      <v-col md="7" sm="7" xs="12">
        <div class="pl-6">
          <p class="display-1 mb-0">{{prod.name}}</p>
          <v-card-actions class="pa-0">
            <p class="headline font-weight-light pt-3">{{numeral(prod.price).format('$0,0.00')}}</p>
            <v-spacer></v-spacer>
            <v-rating v-model="prod.rating"
                      class="" background-color="orange lighten-3"
                      color="orange"
                      half-increments
                      dense>
            </v-rating>
            <span class="body-2	font-weight-thin">({{prod.rating}})</span>
          </v-card-actions>
          <p class="subtitle-1 font-weight-thin">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Tincidunt arcu non sodales neque sodales ut etiam. Lectus arcu bibendum at varius vel pharetra. Morbi tristique senectus et netus et malesuada.
          </p>

          <p class="title">ITEMS</p>
          <v-text-field
              outlined
              style="width:100px"
              :value="1"
              dense>
          </v-text-field>
          <v-btn class="primary white--text" outlined tile dense><v-icon>mdi-cart</v-icon> ADD TO CART</v-btn>
          <v-btn class="ml-4" outlined tile>ADD TO WISHLIST</v-btn>

        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-tabs>
          <v-tab >Description</v-tab>
          <v-tab >Specification</v-tab>
          <v-tab>REVIEWS</v-tab>
          <v-tab-item>
            <p class="pt-10 subtitle-1 font-weight-thin">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
              dolore magna aliqua. Ultricies mi eget mauris pharetra et. Vel pretium lectus quam id leo in vitae turpis
              massa. Orci dapibus ultrices in iaculis nunc. At auctor urna nunc id cursus metus. Integer feugiat
              scelerisque varius morbi enim nunc. Aliquam sem et tortor consequat id porta nibh venenatis cras.
              Pellentesque pulvinar pellentesque habitant morbi tristique senectus et netus. Malesuada nunc vel risus
              commodo viverra maecenas. Neque volutpat ac tincidunt vitae semper quis.
            </p>
          </v-tab-item>
          <v-tab-item>
            <p class="pt-10 subtitle-1 font-weight-thin">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
              dolore magna aliqua. Ultricies mi eget mauris pharetra et. Vel pretium lectus quam id leo in vitae turpis
              massa. Orci dapibus ultrices in iaculis nunc. At auctor urna nunc id cursus metus. Integer feugiat
              scelerisque varius morbi enim nunc. Aliquam sem et tortor consequat id porta nibh venenatis cras.
              Pellentesque pulvinar pellentesque habitant morbi tristique senectus et netus. Malesuada nunc vel risus
              commodo viverra maecenas. Neque volutpat ac tincidunt vitae semper quis.
            </p>
          </v-tab-item>
          <v-tab-item>
<!--            <v-list-->
<!--                three-line="true"-->
<!--                avatar="true">-->
<!--              <v-list-item-group v-model="item" color="primary">-->
<!--                <v-list-item-->
<!--                    v-for="(item, i) in items"-->
<!--                    :key="i"-->
<!--                    inactive="true">-->
<!--                  <v-list-item-avatar>-->
<!--                    <v-img :src="item.avatar"></v-img>-->
<!--                  </v-list-item-avatar>-->
<!--                  <v-list-item-content>-->
<!--                    <v-list-item-title v-html="item.title"></v-list-item-title><v-rating v-model="rating" class="" background-color="warning lighten-3"-->
<!--                                                                                         color="warning" dense></v-rating>-->
<!--                    <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>-->
<!--                  </v-list-item-content>-->
<!--                </v-list-item>-->
<!--              </v-list-item-group>-->
<!--            </v-list>-->
          </v-tab-item>
        </v-tabs>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import Product from '@/domain/Product'
import {inject} from 'inversify-props'
import {IStoreService} from '@/services/IStoreService'
import numeral from 'numeral'

@Component({
             components: { }
           })
export default class ProductView extends Vue {

  @Prop({type: String, required: true, default: null})
  public productId!: string

  @inject()
  private storeService!: IStoreService

  private prod: Product = new Product(0)

  private numeral = numeral

  constructor() {
    super()
  }

  public async mounted() {
    this.prod = await this.storeService.getProduct(parseInt(this.productId))
  }

}
</script>

<style scoped>

</style>
