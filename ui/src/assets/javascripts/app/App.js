import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import routes from './routes';
import Navigation from "../components/Navigation/Navigation";

const App = (props) => (
  <MuiThemeProvider>
    <div style={{height: '100%'}}>
      <Navigation/>
      { routes }
    </div>
  </MuiThemeProvider>
);

export default App;
