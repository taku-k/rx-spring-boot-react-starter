import { combineEpics } from 'redux-observable';
import { epics as todoEpics } from 'features/todo';

export default combineEpics(
  ...todoEpics
);
