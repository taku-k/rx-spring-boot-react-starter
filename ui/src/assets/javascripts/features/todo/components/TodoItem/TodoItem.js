import React, { Component, PropTypes } from 'react';
import {ListItem} from 'material-ui/List';
import ActionInfo from 'material-ui/svg-icons/action/info';

import './TodoItem.scss';

export default class TodoItem extends Component {
  static propTypes = {
    actions: PropTypes.object.isRequired,
    todo: PropTypes.object.isRequired
  };

  constructor(props, context) {
    super(props, context);
  }

  handleRightIconOnClick() {
    const { todo, actions: { requestDelTodo } } = this.props;
    requestDelTodo(todo.id);
  }

  render() {
    const { todo } = this.props;

    return (
      <ListItem
        primaryText={todo.text || '-'}
        rightIcon={<ActionInfo onClick={this.handleRightIconOnClick.bind(this)} />}
      />
    );
  }
}
