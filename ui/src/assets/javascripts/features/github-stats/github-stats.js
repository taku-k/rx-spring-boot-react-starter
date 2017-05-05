import { createStructuredSelector } from 'reselect';
import Rx from 'rxjs/Rx';
const { of, merge } = Rx.Observable;
import ajaxWrapper from '../../helpers/ajaxWrapper';
import { State } from 'models/github-stats';

export const NAME = 'githubStats';

const prefix = 'ui/githubStats';
const CHANGE_USER_NAME = `${prefix}/CHANGE_USER_NAME`;
const START_LOADING = `${prefix}/START_LOADING`;
const FINISH_LOADING = `${prefix}/FINISH_LOADING`;
const FETCH_STATS = `${prefix}/FETCH_STATS`;
const FETCH_STATS_FULFILLED = `${prefix}/FETCH_STATS_FULFILLED`;
const REQUEST_ERROR = `${prefix}/REQUEST_ERROR`;

const initialState: State = {
  userName: "",
  stats: [],
};

export default function reducer(state: State = initialState, action: any = {}): State {
  switch (action.type) {
    case CHANGE_USER_NAME: {
      return {
        ...state,
        userName: action.userName
      }
    }
    case FETCH_STATS_FULFILLED: {
      const stats = action.stats.map(stat => {
        return {
          value: stat.ratio,
          name: stat.name,
          color: stat.color !== null ? stat.color : '#8884d8',
        };
      });
      return {
        ...state,
        stats: stats
      }
    }
    default:
      return state;
  }
}

// Action Creators
const changeUserName = (userName) => ({
  type: CHANGE_USER_NAME,
  userName,
});

const startLoading = () => ({
  type: START_LOADING,
});

const finishLoading = () => ({
  type: FINISH_LOADING,
});

const fetchStats = (userName) => ({
  type: FETCH_STATS,
  userName,
});

const fetchStatsFulfilled = (stats) => ({
  type: FETCH_STATS_FULFILLED,
  stats,
});

const requestError = (message) => ({
  type: REQUEST_ERROR,
  message,
});

// Epic
const fetchStatsEpic = action$ =>
  action$.ofType(FETCH_STATS)
    .flatMap(action =>
      merge(
        ajaxWrapper({url: `/api/github-stats/${action.userName}`})
          .map(data => fetchStatsFulfilled(data.response))
          .catch(error => requestError(error.message)),
        of(changeUserName(action.userName))
    ));


// Selectors
const githubStats = (state) => state[NAME];

export const selector = createStructuredSelector({
  githubStats
});

export const actionCreators = {
  fetchStats,
};

export const epics = [
  fetchStatsEpic
];
