import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { actionCreators, selector } from '../';
import GithubStatsLayout from './GithubStatsLayout';

@connect(selector, (dispatch) => ({
  actions: bindActionCreators(actionCreators, dispatch)
}))
export default class GithubStatsView extends Component {
  render() {
    return (
      <div>
        <GithubStatsLayout {...this.props} />
      </div>
    );
  }
}
