import { combineReducers } from 'redux';
import { routerReducer as routing } from 'react-router-redux';
import todo, { NAME as todoName } from 'features/todo';

export default combineReducers({
  routing,
  [todoName]: todo
});
