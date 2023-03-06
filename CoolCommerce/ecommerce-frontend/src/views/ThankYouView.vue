<template>
  <v-container class="pa-14">
    <v-row>
      <v-col>
        <v-card outlined>
          <v-card-title>Thank you for your order!</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <p>
              Your order has been placed and will be processed shortly. You will receive an email confirmation shortly.
              Here is your confirmation number: <b>{{confirmationId}}</b>
            </p>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";

@Component({
  components: { }
})
export default class ThankYouView extends Vue {

  @Prop({type: String, required: true, default: null})
  public confirmationId!: string

  constructor() {
    super()
  }

}

</script>

<style scoped>

</style>
