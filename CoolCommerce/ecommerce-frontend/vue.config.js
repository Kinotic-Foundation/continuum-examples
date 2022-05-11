const { defineConfig } = require('@vue/cli-service')
module.exports = {

  transpileDependencies: [
    'vuetify'
  ],

  chainWebpack: config => {
    if(process.env.NODE_ENV === 'production'){
      // make sure we don't mangle too much, so we can still use inversify
      config.optimization
          .minimizer('terser')
          .tap(args => {
            const { terserOptions } = args[0]
            terserOptions.keep_classnames = true
            terserOptions.keep_fnames = true
            terserOptions.mangle.reserved = ['eventBus', 'serviceRegistry', 'storeState']
            return args
          })
    }
  }
}
