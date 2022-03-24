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
                <v-form v-model="valid">
                  <v-container>
                    <v-row>
                      <v-col
                          cols="12"
                          md="4">
                        <v-text-field
                            v-model="firstname"
                            :rules="nameRules"
                            label="First name *"
                            required
                        ></v-text-field>
                      </v-col>

                      <v-col
                          cols="12"
                          md="4">
                        <v-text-field
                            v-model="lastname"
                            :rules="nameRules"
                            label="Last name *"
                            required>
                        </v-text-field>
                      </v-col>

                      <v-col
                          cols="12"
                          md="4">
                        <v-text-field
                            v-model="middlename"
                            label="Middle Name/Initial">
                        </v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                          cols="12"
                          md="4">
                        <v-text-field
                            v-model="company"
                            label="Company"
                        ></v-text-field>
                      </v-col>

                      <v-col
                          cols="12"
                          md="4">
                        <v-text-field
                            v-model="email"
                            :rules="emailRules"
                            label="E-mail *"
                            required
                        ></v-text-field>
                      </v-col>

                      <v-col
                          cols="12"
                          md="4">
                        <v-text-field
                            v-model="phone"
                            :rules="phoneRules"
                            label="Phone *"
                            required
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-form>
              <v-btn
                  color="primary"
                  @click="step = 2">
                Continue
              </v-btn>

              <v-btn text>
                Cancel
              </v-btn>
            </v-stepper-content>

            <v-stepper-content step="2">
              <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px">
              </v-card>

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
              <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px">

              </v-card>

              <v-btn
                  color="primary"
                  @click="step = 1">
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

// Function that takes an input value as an argument and return either true / false or a string with an error message
type RuleValidator = (value: any) => string | boolean

@Component({
             components: { }
           })
export default class CheckoutView extends Vue {

  private numeral = numeral

  @inject()
  private storeService!: IStoreService

  @inject()
  private storeState!: IStoreState

  private step = 1

  private valid = false
  private firstname = ''
  private lastname = ''
  private middlename = ''
  private company = ''
  private email = ''
  private phone = ''
  private street = ''
  private country = ''
  private city = ''
  private stateProvince = ''
  private postalCode = ''


  private nameRules: RuleValidator[] = [
    v => !!v || 'Name is required'
  ]

  private emailRules: RuleValidator[] = [
    v => !!v || 'E-mail is required',
    v => /.+@.+/.test(v) || 'E-mail must be valid',
  ]

  private phoneRules: RuleValidator[] = [
    v => !!v || 'Phone is required'
  ]

  constructor() {
    super()
  }

}
</script>

<style scoped>

</style>
