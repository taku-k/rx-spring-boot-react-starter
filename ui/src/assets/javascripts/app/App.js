import React, { PropTypes } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import { AppBar } from 'material-ui';
import IconButton from 'material-ui/IconButton';
import BuildIcon from 'material-ui/svg-icons/action/build';

const App = (props) => (
  <MuiThemeProvider>
    <div>
      <AppBar
        title="Reactor Spring Boot React Starter App"
        iconElementLeft={<IconButton><BuildIcon /></IconButton>}
        className="nav-bar"
      />
      {React.cloneElement({ ...props }.children, { ...props })}
    </div>
  </MuiThemeProvider>
);

App.propTypes = {
  children: PropTypes.element.isRequired
};

export default App;
