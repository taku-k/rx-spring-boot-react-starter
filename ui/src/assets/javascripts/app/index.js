import React from 'react';
import { render } from 'react-dom';
// import { browserHistory } from 'react-router-dom';
// import { syncHistoryWithStore } from 'react-router-redux';
import { AppContainer } from 'react-hot-loader';
import Redbox from 'redbox-react';
import { createBrowserHistory } from 'history';


import Root from './Root';
import configureStore from './store/configureStore';

import injectTapEventPlugin from 'react-tap-event-plugin';
injectTapEventPlugin();


// const history = syncHistoryWithStore(browserHistory, store);
const history = createBrowserHistory();

const store = configureStore(history);
const rootEl = document.getElementById('app');

render(
  <AppContainer errorReporter={Redbox}>
    <Root store={store} history={history} />
  </AppContainer>,
  rootEl
);

if (module.hot) {
  const orgError = console.error; // eslint-disable-line no-console
  console.error = (message) => { // eslint-disable-line no-console
    if (message && message.indexOf('You cannot change <Router routes>;') === -1) {
      // Log the error as normally
      orgError.apply(console, [message]);
    }
  };

  module.hot.accept('./Root', () => {
    const NextApp = require('./Root').default;

    render(
      <AppContainer errorReporter={Redbox}>
        <NextApp store={store} history={history} />
      </AppContainer>,
      rootEl
    );
  });
}
