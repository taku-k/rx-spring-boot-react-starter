import { createStore, applyMiddleware, compose } from 'redux';
import promiseMiddleware from 'redux-promise';
import { createLogger } from 'redux-logger';
import { createEpicMiddleware } from 'redux-observable';
import { connectRouter, routerMiddleware } from 'connected-react-router'

import rootReducer from '../reducer';
import rootEpic from '../epic';
import DevTools from '../DevTools';

export default function configureStore(history, initialState) {
  const epicMiddleware = createEpicMiddleware(rootEpic);

  const middlewares = [
    epicMiddleware,
    promiseMiddleware,
    routerMiddleware(history)
  ];

  if (process.env.NODE_ENV !== `production`) {
    const logger = createLogger();

    middlewares.push(logger);
  }

  const enhancer = compose(
    applyMiddleware(...middlewares),
    window.devToolsExtension ? window.devToolsExtension() : DevTools.instrument()
  );

  const store = createStore(
    connectRouter(history)(rootReducer),
    initialState,
    enhancer);

  if (module.hot) {
    module.hot.accept('../reducer', () => {
      const nextReducer = require('../reducer').default;
      store.replaceReducer(nextReducer);
    });
  }

  return store;
}
