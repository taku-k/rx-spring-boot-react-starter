// @flow

import { createStructuredSelector } from 'reselect';
import Rx from 'rxjs/Rx';
const { of } = Rx.Observable;
import ajaxWrapper from '../../helpers/ajaxWrapper';

import { State } from 'models/todo';

// Action Types
const prefix = 'ui/todo';
const FETCH_TODO = `${prefix}/FETCH_TODO`;
const FETCH_TODO_FULFILLED = `${prefix}/FETCH_TODO_FULFILLED`;
const REQUEST_POST_TODO = `${prefix}/REQUEST_POST_TODO`;
const REQUEST_DEL_TODO = `${prefix}/REQUEST_DEL_TODO`;
const REQUEST_ERROR = `${prefix}/REQUEST_ERROR`;

// This will be used in our root reducer and selectors

export const NAME = 'todo';

const initialState: State = {
  todo: []
};


// Reducer
export default function reducer(state: State = initialState, action: any = {}): State {
  switch (action.type) {
    case FETCH_TODO_FULFILLED: {
      return {
        ...state,
        todo: action.todo
      };
    }
    default:
      return state;
  }
}


// Action Creators
const fetchTodo = () => ({type: FETCH_TODO});
const fetchTodoFulfilled = todo => ({
  type: FETCH_TODO_FULFILLED,
  todo
});
const requestPostTodo = (text) => ({
  type: REQUEST_POST_TODO,
  text
});
const requestDelTodo = (id) => ({
  type: REQUEST_DEL_TODO,
  id
});
const requestError = (message) => ({
  type: REQUEST_ERROR,
  message
});


// Epic
const fetchTodoEpic = action$ =>
  action$.ofType(FETCH_TODO)
    .mergeMapTo(
      ajaxWrapper({url: '/api/todo'})
        .map(data => fetchTodoFulfilled(data.response))
        .catch(error => of(requestError(error.message)))
    );

const requestPostTodoEpic = action$ =>
  action$.ofType(REQUEST_POST_TODO)
    .mergeMap(action =>
      ajaxWrapper({
        url: '/api/todo',
        method: 'POST',
        body: {'text': action.text}
      })
        .mapTo(fetchTodo())
        .catch(error => of(requestError(error.message)))
    );

const requestDelTodoEpic = action$ =>
  action$.ofType(REQUEST_DEL_TODO)
    .mergeMap(action =>
      ajaxWrapper({
        url: `/api/todo/${action.id}`,
        method: 'DELETE'
      })
        .mapTo(fetchTodo())
        .catch(error => of(requestError(error.message)))
    );

// Selectors
const todo = (state) => state[NAME];

export const selector = createStructuredSelector({
  todo
});

export const actionCreators = {
  fetchTodo,
  requestPostTodo,
  requestDelTodo
};

export const epics = [
  fetchTodoEpic,
  requestPostTodoEpic,
  requestDelTodoEpic
];
