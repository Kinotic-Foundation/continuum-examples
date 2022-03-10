<template>
  <v-dialog
      v-model="dialog"
      width="400">
    <template v-slot:activator="{ on:dialog }">
      <v-tooltip bottom>
        <template v-slot:activator="{ on: tooltip }">
          <v-btn icon
                 v-on="{ ...tooltip, ...dialog }">
            <v-icon>mdi-share-variant</v-icon>
          </v-btn>
        </template>
        <span>Share</span>
      </v-tooltip>
    </template>
    <v-card>
      <v-card-title>
        <span class="text-h6 font-weight-bold">Share</span>
        <v-spacer></v-spacer>
        <v-btn
            class="mx-0"
            icon
            @click="dialog = false">
          <v-icon>mdi-close-circle-outline</v-icon>
        </v-btn>
      </v-card-title>
      <v-list>
        <v-list-item>
          <v-list-item-action>
            <v-icon color="indigo">
              mdi-facebook
            </v-icon>
          </v-list-item-action>
          <v-card-title>Facebook</v-card-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon color="cyan">
              mdi-twitter
            </v-icon>
          </v-list-item-action>
          <v-card-title>Twitter</v-card-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-email</v-icon>
          </v-list-item-action>
          <v-card-title>Email</v-card-title>
        </v-list-item>
      </v-list>
      <v-text-field
          :label="copied ? 'Link copied' : 'Click to copy link'"
          class="pa-4"
          readonly
          :value="linkText"
          @click="$event.target.select(); copy()">
      </v-text-field>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component({
             components: { }
           })
export default class ShareDialogButton extends Vue {

  @Prop({ required: true })
  public linkText!: string

  private copied = false

  private dialog = false

  constructor() {
    super()
  }

  private async copy () {
    /* Copy the text inside the text field */
    await navigator.clipboard.writeText(this.linkText);
    this.copied = true
  }

}
</script>

<style scoped>

</style>
