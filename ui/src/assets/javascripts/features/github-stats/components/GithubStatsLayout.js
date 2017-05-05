import React, { Component, PropTypes } from 'react';
import Measure from 'react-measure';

import UsagePieChart from './UsagePieChart';
import Card from '../../../components/Card';
import { FormGroup, FormControl, ControlLabel } from 'react-bootstrap';

import './GithubStatsApp.scss';

export default class GithubStatsLayout extends Component {
  static propTypes = {
    actions: PropTypes.object.isRequired,
    githubStats: PropTypes.object.isRequired,
  };

  constructor(props, context) {
    super(props, context);

    this.state = {
      userName: props.userName,
      dimensions: {
        width: -1,
        height: -1
      },
    };

    this.handleUserNameSubmit.bind(this);
    this.handleUserNameChange.bind(this);
  }

  handleUserNameSubmit = (event) => {
    event.preventDefault();
    this.props.actions.fetchStats(this.state.userName)
  };

  handleUserNameChange = (event) => {
    this.setState({ userName: event.target.value });
  };

  render() {
    const { githubStats: { stats }, actions } = this.props;
    const { width, height } = this.state.dimensions;
    const { userName } = this.state;
    const placeHolder = userName === "" ? "Enter user name" : userName;
    return (
      <div className="github-stats-app">
        <div className="content">
          <Measure
            onMeasure={(dimensions) => {
              this.setState({dimensions})
            }}
          >
            <Card>
              <form onSubmit={this.handleUserNameSubmit}>
                <FormGroup
                  controlId="formBasicText"
                  role="form"
                >
                  <ControlLabel>GitHub user name for statistics</ControlLabel>
                  <FormControl
                    type="text"
                    placeholder={placeHolder}
                    onChange={this.handleUserNameChange}
                  />
                </FormGroup>
              </form>
              <UsagePieChart {...this.props} width={width}/>
            </Card>
          </Measure>
        </div>
      </div>
    )
  };
}
