import React, { Component } from 'react';

import UsagePieChart from './UsagePieChart';
import Card from '../../../components/Card';

import './GithubStatsApp.scss';

export default class GithubStatsLayout extends Component {
  constructor(props, context) {
    super(props, context);
  }

  render() {
    return (
      <div className="github-stats-app">
        <div className="content">
          <Card>
            <UsagePieChart/>
          </Card>
        </div>
      </div>
    )
  };
}
