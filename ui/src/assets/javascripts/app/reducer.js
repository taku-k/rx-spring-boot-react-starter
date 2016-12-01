import { combineReducers } from 'redux';
import { routerReducer as routing } from 'react-router-redux';
import todo, { NAME as todoName } from 'features/todo';
import githubStats, { NAME as githubStatsName } from 'features/github-stats';

export default combineReducers({
  routing,
  [todoName]: todo,
  [githubStatsName]: githubStats
});
