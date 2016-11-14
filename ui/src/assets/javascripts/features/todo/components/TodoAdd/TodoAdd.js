import React, { Component, PropTypes } from 'react';
import TextField from 'material-ui/TextField';

import './TodoAdd.scss';

export default class TodoAdd extends Component {
  static propTypes = {
    actions: PropTypes.object.isRequired
  };

  constructor(props, context) {
    super(props, context);

    this.state = {
      text: ''
    };
  }

  handleOnKeyDown(e) {
    if (e.keyCode === 13) {
      this.props.actions.requestPostTodo(this.state.text);
    }
  }

  handleTextChange(e) {
    this.setState({ text: e.target.value });
  }

  render() {
    return (
      <div id="todo-text">
        <TextField
          className="add-todo"
          hintText="A new todo item..."
          floatingLabelText="What Task?"
          onChange={this.handleTextChange.bind(this)}
          onKeyDown={this.handleOnKeyDown.bind(this)}
        />
      </div>
    );
  }
}
