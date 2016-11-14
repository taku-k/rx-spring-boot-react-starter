import { createStore, applyMiddleware, compose } from 'redux';
import promiseMiddleware from 'redux-promise';
import createLogger from 'redux-logger';
import { createEpicMiddleware } from 'redux-observable';

import rootReducer from '../reducer';
import rootEpic from '../epic';
import DevTools from '../DevTools';

const logger = createLogger();

const epicMiddleware = createEpicMiddleware(rootEpic);

const middlewares = [epicMiddleware, promiseMiddleware, logger, require('redux-immutable-state-invariant')()];

const enhancer = compose(
  applyMiddleware(...middlewares),
  window.devToolsExtension ? window.devToolsExtension() : DevTools.instrument()
);

export default function configureStore(initialState) {
  const store = createStore(rootReducer, initialState, enhancer);

  if (module.hot) {
    module.hot.accept('../reducer', () => {
      const nextReducer = require('../reducer').default;
      store.replaceReducer(nextReducer);
    });
  }

  return store;
}
