import React, { PropTypes } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

const App = (props) => (
  <MuiThemeProvider>
    {React.cloneElement({ ...props }.children, { ...props })}
  </MuiThemeProvider>
);

App.propTypes = {
  children: PropTypes.element.isRequired
};

export default App;
