import React, { Component, PropTypes } from 'react';
import Measure from 'react-measure';
import {PieChart, Pie, Sector} from 'recharts';

import './UsagePieChart.scss';

const data = [{name: 'Group A', value: 400}, {name: 'Group B', value: 300},
  {name: 'Group C', value: 300}, {name: 'Group D', value: 200}];

export default class UsagePieChart extends Component {
  static propTypes = {
    githubStats: PropTypes.object.isRequired,
  };

  constructor(props, context) {
    super(props, context);

    this.state={
      activeIndex: 0,
      options:{
        title: 'My Daily Activities'
      },
    };

    this.onPieEnter.bind(this);
  }

  onPieEnter = (data, index) => {
    this.setState({
      activeIndex: index,
    });
  };

  renderActiveShape = (props) => {
    const RADIAN = Math.PI / 180;
    const { cx, cy, midAngle, innerRadius, outerRadius, startAngle, endAngle,
      fill, payload, percent, value } = props;
    const sin = Math.sin(-RADIAN * midAngle);
    const cos = Math.cos(-RADIAN * midAngle);
    const sx = cx + (outerRadius + 10) * cos;
    const sy = cy + (outerRadius + 10) * sin;
    const mx = cx + (outerRadius + 30) * cos;
    const my = cy + (outerRadius + 30) * sin;
    const ex = mx + (cos >= 0 ? 1 : -1) * 22;
    const ey = my;
    const textAnchor = cos >= 0 ? 'start' : 'end';

    return (
      <g>
        <text x={cx} y={cy} dy={8} textAnchor="middle" fill={fill}>{payload.name}</text>
        <Sector
          cx={cx}
          cy={cy}
          innerRadius={innerRadius}
          outerRadius={outerRadius}
          startAngle={startAngle}
          endAngle={endAngle}
          fill={payload.color}
        />
        <Sector
          cx={cx}
          cy={cy}
          startAngle={startAngle}
          endAngle={endAngle}
          innerRadius={outerRadius + 6}
          outerRadius={outerRadius + 10}
          fill={payload.color}
        />
        <path d={`M${sx},${sy}L${mx},${my}L${ex},${ey}`} stroke={payload.color} fill="none"/>
        <circle cx={ex} cy={ey} r={2} fill={payload.color} stroke="none"/>
        <text x={ex + (cos >= 0 ? 1 : -1) * 12} y={ey} textAnchor={textAnchor} fill="#333">{`Usage ${value}`}</text>
      </g>
    );
  };

  render() {
    const { githubStats: { stats } } = this.props;
    return (
      <div>
        <PieChart width={this.props.width} height={400} onMouseEnter={this.onPieEnter}>
          <Pie
            activeIndex={this.state.activeIndex}
            activeShape={this.renderActiveShape}
            data={stats}
            cx={this.props.width / 2 - 100}
            cy={200}
            innerRadius={110}
            outerRadius={140}
            fill="#8884d8"/>
        </PieChart>
      </div>
    );
  }
}
