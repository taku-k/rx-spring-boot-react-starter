import React, { Component, PropTypes } from 'react';
import { Card as MaterialCard, CardText } from 'material-ui';

import './Card.scss';

export default class Card extends Component {
  static propTypes = {
    children: PropTypes.oneOfType([
      PropTypes.arrayOf(PropTypes.node),
      PropTypes.node,
    ]).isRequired
  };

  render() {
    return (
      <MaterialCard className="card">
        <CardText>
          {this.props.children}
        </CardText>
      </MaterialCard>
    );
  }
}
