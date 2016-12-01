import { createStructuredSelector } from 'reselect';
import Rx from 'rxjs/Rx';
import ajaxWrapper from '../../helpers/ajaxWrapper';

export const NAME = 'github-stats';

const prefix = 'ui/github-stats';

import { State } from 'models/github-stats';

const initialState: State = {
};

export default function reducer(state: State = initialState, action: any = {}): State {
  switch (action.type) {
    default:
      return state;
  }
}

// Selectors
const githubStats = (state) => state[NAME];

export const selector = createStructuredSelector({
  githubStats
});

export const actionCreators = {
};
