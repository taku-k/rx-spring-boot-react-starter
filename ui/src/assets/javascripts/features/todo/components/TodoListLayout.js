import React, { Component, PropTypes } from 'react';
import { AppBar } from 'material-ui';
import { List } from 'material-ui/List';
import TodoItem from './TodoItem';
import TodoAdd from './TodoAdd';
import Subheader from 'material-ui/Subheader';
import Card from '../../../components/Card';

import './TodoListApp.scss';

export default class TodoListLayout extends Component {
  static propTypes = {
    actions: PropTypes.object.isRequired,
    todo: PropTypes.object.isRequired
  };

  constructor(props, context) {
    super(props, context);
  }

  componentDidMount = () => {
    this.props.actions.fetchTodo();
  };

  render() {
    const { todo: { todo }, actions } = this.props;

    return (
      <div className="todo-list-app">
        <div className="content">
          <Card>
            <TodoAdd actions={actions}/>
            <List>
              <Subheader>Todo List</Subheader>
              {todo.map(
                todo => (
                  <TodoItem
                    key={todo.id}
                    todo={todo}
                    actions={actions} />
                )
              )}
            </List>
          </Card>
        </div>
      </div>
    );
  }
}
