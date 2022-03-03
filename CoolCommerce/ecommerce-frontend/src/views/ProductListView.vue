<template>
    <v-container fluid>
      <v-row dense>
        <v-col
            v-for="product in products"
            :key="product.id"
            :cols="3"
        >
          <v-card>
            <v-img
                :src="product.imageThumbnailUrl"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200px"
            >
              <v-card-title v-text="product.name"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
</template>

<script lang="ts">
import {Component, Prop, Vue, Watch} from 'vue-property-decorator';
import {inject} from 'inversify-props'
import {IStoreService} from '@/services/StoreService'
import Product from '@/domain/Product'

@Component({
  components: {
  },
})
export default class ProductListView extends Vue {

  @Prop({type: String, required: false, default: null})
  public categoryId!: string

  @inject()
  private storeService!: IStoreService

  private loading = false

  private products: Product[] = []

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
    let data = await this.storeService.getAllProductsForCategoryId(parseInt(this.categoryId))
    this.products.push(...data)

    this.loading = false
  }


}
</script>
