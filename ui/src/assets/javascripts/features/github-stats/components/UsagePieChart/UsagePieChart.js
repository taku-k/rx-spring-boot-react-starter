import React, { Component } from 'react';
import { Chart } from 'react-google-charts';

import './UsagePieChart.scss';

export default class UsagePieChart extends Component {
  constructor(props, context) {
    super(props, context);

    this.state={
      options:{
        title: 'My Daily Activities'
      },
      data:[
        ['Task', 'Hours per Day'],
        ['Work',     11],
        ['Eat',      2],
        ['Commute',  2],
        ['Watch TV', 2],
        ['Sleep',    7]
      ]
    };
  }

  render() {
    return (
      <div>
      <Chart
        chartType="PieChart"
        data={this.state.data}
        options={this.state.options}
        width="100%"
        height="400px"
      />
      </div>
    );
  }
}
