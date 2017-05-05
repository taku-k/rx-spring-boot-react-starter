import { combineEpics } from 'redux-observable';
import { epics as todoEpics } from 'features/todo';
import { epics as githubStatsEpics } from 'features/github-stats';

export default combineEpics(
  ...todoEpics,
  ...githubStatsEpics,
);
