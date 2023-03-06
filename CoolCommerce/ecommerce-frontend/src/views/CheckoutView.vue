<template>
  <v-container class="pa-14">
    <v-row>
      <v-col>
        <v-stepper v-model="step">
          <v-stepper-header>
            <v-stepper-step
                :complete="step > 1"
                step="1">
              BILLING ADDRESS
            </v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step
                :complete="step > 2"
                step="2">
              DELIVERY METHOD
            </v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step step="3">
              PAYMENT METHOD
            </v-stepper-step>
          </v-stepper-header>

          <v-stepper-items>
            <v-stepper-content step="1">
              <v-form v-model="customerInfoValid">
                <v-container fluid>
                  <v-row>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.customerInfo.firstname"
                          :rules="requiredRules"
                          label="First name *"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.customerInfo.lastname"
                          :rules="requiredRules"
                          label="Last name *"
                          required>
                      </v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.customerInfo.middlename"
                          label="Middle Name/Initial">
                      </v-text-field>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.customerInfo.company"
                          label="Company"
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.customerInfo.email"
                          :rules="emailRules"
                          label="E-mail *"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.customerInfo.phone"
                          :rules="requiredRules"
                          label="Phone *"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col
                        cols="12">
                      <v-text-field
                          v-model="info.customerInfo.street"
                          :rules="requiredRules"
                          label="Street *"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col
                        cols="12"
                        md="3">
                      <v-text-field
                          v-model="info.customerInfo.country"
                          :rules="requiredRules"
                          label="Country *"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="3">
                      <v-text-field
                          v-model="info.customerInfo.city"
                          :rules="requiredRules"
                          label="City *"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="3">
                      <v-text-field
                          v-model="info.customerInfo.stateProvince"
                          :rules="requiredRules"
                          label="State/Province *"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="3">
                      <v-text-field
                          v-model="info.customerInfo.postalCode"
                          :rules="requiredRules"
                          label="Zip/Postal Code *"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-form>
              <v-btn
                  color="primary"
                  :disabled="!customerInfoValid"
                  @click="step = 2">
                Continue
              </v-btn>

              <v-btn text>
                Cancel
              </v-btn>
            </v-stepper-content>

            <v-stepper-content step="2">
              <v-container fluid>
                <v-radio-group v-model="info.shippingOption">
                  <template v-slot:label>
                    <div>Delivery Method</div>
                  </template>
                  <v-radio value="free">
                    <template v-slot:label>
                      <div><strong>Free Delivery</strong> $0.00 / Delivery in 7 to 14 business Days</div>
                    </template>
                  </v-radio>
                  <v-radio value="standard">
                    <template v-slot:label>
                      <div><strong>Standard Delivery</strong> $7.99 / Delivery in 5 to 7 business Days</div>
                    </template>
                  </v-radio>
                  <v-radio value="express">
                    <template v-slot:label>
                      <div><strong>Express Delivery</strong> $29.99 / Delivery in 1 business Days</div>
                    </template>
                  </v-radio>
                </v-radio-group>
              </v-container>

              <v-btn
                  color="primary"
                  @click="step = 3">
                Continue
              </v-btn>

              <v-btn text>
                Cancel
              </v-btn>
            </v-stepper-content>

            <v-stepper-content step="3">
              <v-form v-model="paymentInfoValid">
                <v-container fluid>
                  <v-row>
                    <v-col
                        cols="12"
                        md="6">
                      <v-text-field
                          v-model="info.paymentInfo.cardHolderName"
                          :rules="requiredRules"
                          label="Card holder name *"
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col
                        cols="12"
                        md="6">
                      <v-text-field
                          v-model="info.paymentInfo.cardNumber"
                          :rules="requiredRules"
                          label="Card number *"
                          required>
                      </v-text-field>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col
                        cols="12"
                        md="4">
                      <v-select
                          v-model="info.paymentInfo.month"
                          :items="months"
                          :rules="requiredRules"
                          label="Expired month *"
                      ></v-select>
                    </v-col>
                    <v-col
                        cols="12"
                        md="4">
                      <v-select
                          v-model="info.paymentInfo.year"
                          :items="years"
                          :rules="requiredRules"
                          label="Expired year *"
                      ></v-select>
                    </v-col>
                    <v-col
                        cols="12"
                        md="4">
                      <v-text-field
                          v-model="info.paymentInfo.cvv"
                          :rules="requiredRules"
                          label="CVV *"
                          required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-form>

              <v-btn
                  color="primary"
                  :disabled="!paymentInfoValid"
                  @click="checkout">
                Continue
              </v-btn>

              <v-btn text>
                Cancel
              </v-btn>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
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
import {CheckoutInfo} from '@/domain/CheckoutInfo'
import { enUS } from 'date-fns/locale'
import getYear from 'date-fns/getYear'

// Function that takes an input value as an argument and return either true / false or a string with an error message
type RuleValidator = (value: any) => string | boolean

type Month = {value: number, text: string}

@Component({
             components: { }
           })
export default class CheckoutView extends Vue {

  private numeral = numeral

  @inject()
  private storeService!: IStoreService

  @inject()
  private storeState!: IStoreState

  private info = new CheckoutInfo()

  private customerInfoValid = false
  private paymentInfoValid = false
  private step = 1

  private requiredRules: RuleValidator[] = [
    v => !!v || 'Field is required'
  ]

  private emailRules: RuleValidator[] = [
    v => !!v || 'E-mail is required',
    v => /.+@.+/.test(v) || 'E-mail must be valid',
  ]

  private months: Month[] = []
  private years: number[] = []

  constructor() {
    super()

    for (let i = 0; i < 12; i++) {
      this.months.push( {value: i, text: enUS.localize?.month(i) })
    }

    let year = getYear(new Date())
    for(let i = year; i < year + 10; i++){
      this.years.push(i)
    }
  }

  private async checkout() {
    const confirmationId = await this.storeService.checkout(this.info)
    await this.$router.replace({path: `/thank_you/${confirmationId}`})
  }

}
</script>

<style scoped>

</style>
