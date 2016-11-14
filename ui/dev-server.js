const path = require('path');
const express = require('express');
const webpack = require('webpack');
const webpackDevMiddleware = require('webpack-dev-middleware');
const webpackHotMiddleware = require('webpack-hot-middleware');
const DashboardPlugin = require('webpack-dashboard/plugin');
const config = require('./config/webpack.config.development');
const proxy = require('http-proxy-middleware');

const app = express();
const compiler = webpack(config);

compiler.apply(new DashboardPlugin());

const host = process.env.HOST || 'localhost';
const port = process.env.PORT || 3000;

app.use(webpackDevMiddleware(compiler, {
  noInfo: true,
  publicPath: config.output.publicPath,
  stats: {
    colors: true
  },
  historyApiFallback: true
}));

app.use(webpackHotMiddleware(compiler));

app.use(proxy('/api', {target: 'http://' + host + ':8090'}));

app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, './src/assets/index.html'));
});

app.listen(port, host, (err) => {
  if (err) {
    console.log(err);
    return;
  }

  console.log('Listening at http://%s:%s', host, port);
});
