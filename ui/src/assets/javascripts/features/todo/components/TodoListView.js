import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { actionCreators as todoActions, selector } from '../';
import TodoListLayout from './TodoListLayout';

@connect(selector, (dispatch) => ({
  actions: bindActionCreators(todoActions, dispatch)
}))
export default class TodoListView extends Component {
  render() {
    return (
      <div>
        <TodoListLayout {...this.props} />
      </div>
    );
  }
}
