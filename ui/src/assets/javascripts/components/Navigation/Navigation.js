import React, { Component, PropTypes } from 'react';
import { AppBar, MenuItem } from 'material-ui';
import IconButton from 'material-ui/IconButton';
import BuildIcon from 'material-ui/svg-icons/action/build';
import Drawer from 'material-ui/Drawer';
import ContentClear from 'material-ui/svg-icons/content/clear';
import Divider from 'material-ui/Divider';
import { Link } from 'react-router-dom';

export default class Navigation extends Component {
  constructor() {
    super();
    this.state = {
      open: false,
    };
  }

  handleLeftIconToggle = () => this.setState({open: !this.state.open});

  handleDrawerClose = () => this.setState({open: false});

  render() {
    return (
      <div>
        <AppBar
          title="Rx React"
          iconElementLeft={<IconButton><BuildIcon /></IconButton>}
          className="nav-bar"
          onLeftIconButtonTouchTap={this.handleLeftIconToggle}
        />
        <Drawer open={this.state.open}>
          <ContentClear onClick={this.handleDrawerClose}/>
          <Divider/>
          <MenuItem onTouchTap={this.handleDrawerClose}><Link to="/">Todo</Link></MenuItem>
          <MenuItem onTouchTap={this.handleDrawerClose}><Link to="/github">GitHub Stats</Link></MenuItem>
        </Drawer>
      </div>
    );
  }
}
