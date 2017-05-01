
const merge = require('webpack-merge');
const webpack = require('webpack');
const config = require('./webpack.config.base');
const path = require('path');

const ENV = {
  'process.env': {
    'NODE_ENV': JSON.stringify('development')
  },
  __DEV__: JSON.stringify(JSON.parse(process.env.DEBUG || 'true'))
};

module.exports = merge(config, {
  cache: true,
  devtool: 'inline-source-map',
  entry: {
    application: [
      'webpack-hot-middleware/client',
      'react-hot-loader/patch',
      'main'
    ],
    vendor: ['react', 'react-dom', 'react-redux', 'react-router',
      'react-router-redux', 'redux', 'material-ui']
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new webpack.DefinePlugin(ENV),
    new webpack.LoaderOptionsPlugin({
      options: {
        context: __dirname,
        postcss: []
      }
    }),
  ],
  module: {
    loaders: [
      // Sass
      {
        test: /\.scss$/,
        include: [
          path.resolve(__dirname, '../src/assets/javascripts'),
          path.resolve(__dirname, '../src/assets/styles'),
          path.resolve(__dirname, '../src/scripts')
        ],
        loaders: [
          'style-loader',
          'css-loader',
          'postcss-loader',
          {loader: 'sass-loader', query: {outputStyle: 'expanded'}}
        ]
      },
      // CSS
      {
        test: /\.css$/,
        loaders: [
          'style-loader',
          'css-loader',
          'postcss-loader'
        ]
      }
    ]
  }
});
